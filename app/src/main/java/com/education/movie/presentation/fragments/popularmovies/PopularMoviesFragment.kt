package com.education.movie.presentation.fragments.popularmovies

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.movie.R
import com.education.movie.data.models.popularmovies.PageOfMoviesResponse
import com.education.movie.databinding.FragmentPopularMoviesBinding
import com.education.movie.presentation.viewmodel.popularmovies.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by viewModels()
    private var adapter = PopularMoviesAdapter(::onItemClick)
    private lateinit var binding: FragmentPopularMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataToRecyclerViewInit()
        recyclerViewInit()
    }

    private fun recyclerViewInit() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun initScrollListener(movies: PageOfMoviesResponse) {
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastItemCount = layoutManager.findLastVisibleItemPosition()
                if (totalItemCount - differenceNumber == lastItemCount && movies.page == viewModel.numberOfPage) {
                    Toast.makeText(
                        requireContext(),
                        "Page ${viewModel.numberOfPage} was downloaded",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.numberOfPage++
                    viewModel.showPageOfMovies(viewModel.numberOfPage)
                    viewModel.listOfDownloadedMovies.addAll(movies.results)
                }
            }
        })
    }

    private fun dataToRecyclerViewInit() {
        viewModel.pageOfMovies.observe(viewLifecycleOwner) { response ->
            try {
                response.body()?.let { movies ->
                    if (viewModel.numberOfPage == 1) {
                        viewModel.listOfDownloadedMovies.addAll(movies.results)
                        viewModel.numberOfPage++
                        viewModel.showPageOfMovies(viewModel.numberOfPage)
                    }
                    initScrollListener(movies)
                    adapter.setData(viewModel.listOfDownloadedMovies)
                }
            } catch (e: Exception) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Exit") { _, _ ->
                    exitProcess(0)
                }
                builder.setTitle("Error")
                builder.setMessage("Connection with server is lost...")
                builder.create().show()
            }
        }
    }

    private fun onItemClick(position: Int) {
        val selectedItem = Bundle()
        val list = viewModel.listOfDownloadedMovies[position].id
        selectedItem.putInt(PopularMoviesViewModel.BUNDLE_KEY, list)
        findNavController().navigate(
            R.id.action_moviesFragment_to_movieFragment,
            selectedItem
        )
    }

    companion object {
        private const val differenceNumber = 3
    }
}
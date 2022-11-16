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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.education.movie.R
import com.education.movie.data.models.popularmovies.ListOfMoviesResponse
import com.education.movie.data.models.popularmovies.PageOfMoviesResponse
import com.education.movie.databinding.FragmentPopularMoviesBinding
import com.education.movie.presentation.viewmodel.popularmovies.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_popular_movies.recycler_view
import kotlin.system.exitProcess

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by viewModels()
    private var adapter = PopularMoviesAdapter(::onItemClick)
    private lateinit var binding: FragmentPopularMoviesBinding
    private var listOfMovies: ArrayList<ListOfMoviesResponse> = ArrayList()
    private var pageCounter = 1
    private var pageOfMovies = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        pageOfMovies = 1
        viewModel.showPageOfMovies(pageOfMovies)
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
    }

    private fun initScrollListener(page: Int) {
        val layoutManager = recycler_view.layoutManager as LinearLayoutManager
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = layoutManager.itemCount
                val lastItemCount = layoutManager.findLastVisibleItemPosition()
                println()
                if (totalItemCount - differenceNumber == lastItemCount && page == pageCounter) {
                    pageOfMovies++
                    Toast.makeText(
                        requireContext(),
                        "Page $pageOfMovies was downloaded",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.showPageOfMovies(pageOfMovies)
                    pageCounter++
                }
            }
        })
    }

    private fun dataToRecyclerViewInit() {
        viewModel.listOfMovies.observe(viewLifecycleOwner) { response ->
            try {
                response.body()?.let { movies ->
                    initScrollListener(pageCounter)
                    listOfMovies.addAll(movies.results)
                    adapter.setData(listOfMovies)
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
        val list = listOfMovies[position].id
        selectedItem.putInt(PopularMoviesViewModel.BUNDLE_KEY, list)
        findNavController().navigate(
            R.id.action_moviesFragment_to_movieFragment,
            selectedItem
        )
    }

    companion object {
        private const val differenceNumber = 2
    }
}
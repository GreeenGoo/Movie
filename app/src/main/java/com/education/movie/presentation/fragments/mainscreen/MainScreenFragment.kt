package com.education.movie.presentation.fragments.mainscreen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.education.movie.R
import com.education.movie.data.models.mainscreen.PageOfMoviesResponse
import com.education.movie.databinding.FragmentMainScreenBinding
import com.education.movie.presentation.viewmodel.mainscreen.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()
    private var adapter = MainScreenAdapter(::onItemClick)
    private lateinit var binding: FragmentMainScreenBinding
    private var moviesList = PageOfMoviesResponse(0, emptyList(), 0, 0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelInit()
        recyclerViewInit()
    }

    private fun recyclerViewInit() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
    }

    private fun viewModelInit() {
        viewModel.listOfMovies.observe(viewLifecycleOwner) { response ->
            try {
                response.body()?.let { movies ->
                    adapter.setData(movies)
                    moviesList = movies
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

    private fun onItemClick (position: Int){
        val selectedItem = Bundle()
        val list = moviesList.results[position].id
        selectedItem.putInt(MainScreenViewModel.BUNDLE_KEY, list)
        findNavController().navigate(
            R.id.action_moviesFragment_to_movieFragment,
            selectedItem
        )
    }
}
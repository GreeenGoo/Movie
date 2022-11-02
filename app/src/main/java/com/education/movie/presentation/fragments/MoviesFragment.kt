package com.education.movie.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.education.movie.databinding.FragmentMoviesBinding
import com.education.movie.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private var adapter = MoviesAdapter()
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
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
}
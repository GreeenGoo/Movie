package com.education.movie.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.education.movie.databinding.FragmentMoviesBinding
import com.education.movie.presentation.MainActivity.Companion.mLayoutManagerState
import com.education.movie.presentation.viewmodel.MoviesViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.recycler_view

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
//        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getPageOfMovies()
        viewModel.myResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let { movies ->
                    adapter.setData(movies)
                }
            } else {
                Toast.makeText(requireContext(), response.code(), Toast.LENGTH_SHORT).show()
            }
        }
        if (mLayoutManagerState != null) {
            recycler_view.layoutManager?.onRestoreInstanceState(mLayoutManagerState)
        }
    }
}
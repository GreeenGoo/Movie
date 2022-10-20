package com.education.movie.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.education.movie.R
import com.education.movie.data.repository.MoviesRepository
import com.education.movie.databinding.FragmentMoviesBinding
import com.education.movie.presentation.viewmodel.MoviesViewModel
import com.education.movie.presentation.viewmodel.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.recycler_view


class MoviesFragment : Fragment() {

    private var viewModel: MoviesViewModel? = null
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { MoviesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        recycler_view.adapter = adapter
    }

    private fun setupViewModel() {
        val repository = MoviesRepository()
        val viewModelFactory = MoviesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]
        viewModel!!.getPageOfMovies()
        viewModel!!.myResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapter.setData(it) }
            } else {
                Toast.makeText(requireContext(), response.code(), Toast.LENGTH_SHORT).show()
            }

        }
    }
}
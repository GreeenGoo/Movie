package com.education.movie.presentation.fragments.movie

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.education.movie.R
import com.education.movie.data.models.movie.GenresResponse
import com.education.movie.data.models.movie.ProductionCompaniesResponse
import com.education.movie.data.models.movie.ProductionCountriesResponse
import com.education.movie.data.models.movie.SpokenLanguagesResponse
import com.education.movie.databinding.FragmentMovieDetailsBinding
import com.education.movie.presentation.fragments.popularmovies.PopularMoviesAdapter
import com.education.movie.presentation.viewmodel.popularmovies.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by viewModels()
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInit()
        binding.backPageIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun dataInit() {
        val bundleNote: Int? = arguments?.getInt(PopularMoviesViewModel.BUNDLE_KEY)
        bundleNote?.let { id ->
            viewModel.getMovie(id)
            viewModel.movie.observe(viewLifecycleOwner) { response ->
                try {
                    binding.titleAndReleaseDate.text =
                        "${response.body()?.title} (${getYearFromDate(response.body()?.releaseDate.toString())})"
                    uploadPicture(binding.poster, response.body()!!.posterPath)
                    binding.releaseDate.text = convertDataToRightFormat(
                        response.body()!!.releaseDate
                    )
                    binding.runtime.text =
                        "${response.body()!!.runtime / 60}h ${response.body()!!.runtime % 60}m"
                    var voiceAverage = String.format("%.1f", response.body()!!.voteAverage)
                    voiceAverage = voiceAverage.replace(",", ".")
                    binding.vote.text =
                        "$voiceAverage (${response.body()!!.voteCount} voices)"
                    addGenresTab(response.body()!!.genres)
                    addNewLanguageTab(response.body()!!.spokenLanguages)
                    if (response.body()!!.overview.isEmpty()) {
                        binding.details.visibility = View.GONE
                        binding.detailsTitle.visibility = View.GONE
                    } else {
                        binding.details.text = response.body()!!.overview
                    }
                    if (response.body()!!.productionCompanies.isEmpty()) {
                        binding.productionCompaniesTitle.visibility = View.GONE
                        binding.productionCompanies.visibility = View.GONE
                    } else {
                        binding.productionCompanies.text = createCompaniesLine(
                            response.body()!!.productionCompanies
                        )
                    }
                    if (response.body()!!.budget == 0) {
                        binding.budgetTitle.visibility = View.GONE
                        binding.budget.visibility = View.GONE
                    } else {
                        binding.budget.text = "${response.body()!!.budget}$"
                    }
                    if (response.body()!!.revenue == 0) {
                        binding.revenueTitle.visibility = View.GONE
                        binding.revenue.visibility = View.GONE
                    } else {
                        binding.revenue.text = "${response.body()!!.revenue}$"
                    }
                    if (response.body()!!.productionCountries.isEmpty()) {
                        binding.productionCountries.visibility = View.GONE
                        binding.productionCountriesTitle.visibility = View.GONE
                    } else {
                        binding.productionCountries.text = createCountriesLine(
                            response.body()!!.productionCountries
                        )
                    }
                    if (response.body()!!.homepage.isEmpty()) {
                        binding.homepageTitle.visibility = View.GONE
                        binding.homepage.visibility = View.GONE
                    } else {
                        binding.homepage.text = response.body()!!.homepage
                    }
                } catch (e: Exception) {
                    Log.e("Movie Mistake", "There is a mistake with downloading the movie.")
                }
            }
        }
    }

    private fun addGenresTab(genres: List<GenresResponse>) {
        val flexboxLayout = binding.genresFlexboxLayout
        for (genre in genres) {
            val genreTextView = TextView(requireContext())
            genreTextView.text = genre.name
            genreTextView.textSize = 15f
            genreTextView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.button_shape)
            genreTextView.setPadding(50, 20, 50, 20)
            genreTextView.setTextColor(Color.WHITE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                genreTextView.typeface = resources.getFont(R.font.lato_regular)
            }
            flexboxLayout.addView(genreTextView)
        }
    }

    private fun addNewLanguageTab(genres: List<SpokenLanguagesResponse>) {
        val flexboxLayout = binding.languagesFlexboxLayout
        for (genre in genres) {
            val genreTextView = TextView(requireContext())
            genreTextView.text = genre.name
            genreTextView.textSize = 15f
            genreTextView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.button_shape)
            genreTextView.setPadding(50, 20, 50, 20)
            genreTextView.setTextColor(Color.WHITE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                genreTextView.typeface = resources.getFont(R.font.lato_regular)
            }
            flexboxLayout.addView(genreTextView)
        }
    }

    private fun uploadPicture(imageView: ImageView, uri: String) {
        Glide.with(requireContext())
            .load(Uri.parse("${PopularMoviesAdapter.BASE_URI_FOR_IMAGES}${uri}")).centerCrop()
            .into(imageView)
    }


    private fun getYearFromDate(date: String): String {
        val yearMonthDay = date.split(dashSeparator)
        return yearMonthDay[0]
    }

    private fun convertDataToRightFormat(data: String): String {
        return data.split(dashSeparator).reversed().joinToString(dotSeparator)
    }

    private fun createCountriesLine(list: List<ProductionCountriesResponse>): String {
        var line = ""
        for (i in list.indices) {
            line += if (i == 0) list[i].name
            else ", ${list[i].name}"
        }
        return line
    }

    private fun createCompaniesLine(list: List<ProductionCompaniesResponse>): String {
        var line = ""
        for (i in list.indices) {
            line += if (i == 0) list[i].name
            else ", ${list[i].name}"
        }
        return line
    }

    companion object {
        private const val dotSeparator = "."
        private const val dashSeparator = "-"
    }
}
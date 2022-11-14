package com.education.movie.presentation.fragments.movie

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.education.movie.R
import com.education.movie.data.models.movie.GenresResponse
import com.education.movie.data.models.movie.ProductionCompaniesResponse
import com.education.movie.data.models.movie.ProductionCountriesResponse
import com.education.movie.data.models.movie.SpokenLanguagesResponse
import com.education.movie.presentation.fragments.mainscreen.MainScreenAdapter
import com.education.movie.presentation.viewmodel.mainscreen.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.background_poster
import kotlinx.android.synthetic.main.fragment_movie_details.budget
import kotlinx.android.synthetic.main.fragment_movie_details.companies
import kotlinx.android.synthetic.main.fragment_movie_details.homepage
import kotlinx.android.synthetic.main.fragment_movie_details.movie_genres
import kotlinx.android.synthetic.main.fragment_movie_details.overview
import kotlinx.android.synthetic.main.fragment_movie_details.poster
import kotlinx.android.synthetic.main.fragment_movie_details.production_countries
import kotlinx.android.synthetic.main.fragment_movie_details.release_date
import kotlinx.android.synthetic.main.fragment_movie_details.revenue
import kotlinx.android.synthetic.main.fragment_movie_details.runtime
import kotlinx.android.synthetic.main.fragment_movie_details.spoken_language
import kotlinx.android.synthetic.main.fragment_movie_details.tagline
import kotlinx.android.synthetic.main.fragment_movie_details.title_and_release_date
import kotlinx.android.synthetic.main.fragment_movie_details.vote

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInit()
    }

    @SuppressLint("SetTextI18n")
    private fun dataInit() {
        val bundleNote: Int? = arguments?.getInt(PopularMoviesViewModel.BUNDLE_KEY)
        bundleNote?.let { id ->
            viewModel.getMovie(id)
            viewModel.movie.observe(viewLifecycleOwner) { response ->
                try {
                    uploadPicture(background_poster, response.body()!!.backdropPath)
                    title_and_release_date.text =
                        "${response.body()?.title} (${getYearFromDate(response.body()?.releaseDate.toString())})"
                    uploadPicture(poster, response.body()!!.posterPath)
                    if (response.body()!!.tagline.isEmpty()) {
                        tagline.visibility = View.GONE
                    } else {
                        tagline.text =
                            response.body()!!.tagline
                    }
                    if (response.body()!!.releaseDate.isEmpty()) {
                        release_date.visibility = View.GONE
                    } else {
                        release_date.text =
                            getString(R.string.release_date_title) + convertDataToRightFormat(
                                response.body()!!.releaseDate
                            )
                    }
                    if (response.body()!!.genres.isEmpty()) {
                        movie_genres.visibility = View.GONE
                    } else {
                        movie_genres.text =
                            getString(R.string.genres_title) + createGenresLine(response.body()!!.genres)
                    }
                    if (response.body()!!.runtime == 0) {
                        runtime.visibility = View.GONE
                    } else {
                        runtime.text =
                            getString(R.string.runtime_title) + "${response.body()!!.runtime / 60} h ${response.body()!!.runtime % 60} min"
                    }
                    if (response.body()!!.voteAverage == 0f || response.body()!!.voteCount == 0) {
                        vote.visibility = View.GONE
                    } else {
                        vote.text =
                            getString(R.string.vote_title) + "${response.body()!!.voteAverage} (${response.body()!!.voteCount} voices)"
                    }
                    if (response.body()!!.homepage.isEmpty()) {
                        homepage.visibility = View.GONE
                    } else {
                        homepage.text =
                            getString(R.string.home_page_title) + response.body()!!.homepage
                    }
                    if (response.body()!!.productionCountries.isEmpty()) {
                        production_countries.visibility = View.GONE
                    } else {
                        production_countries.text =
                            getString(R.string.production_countries_title) + createCountriesLine(
                                response.body()!!.productionCountries
                            )
                    }
                    if (response.body()!!.spokenLanguages.isEmpty()) {
                        spoken_language.visibility = View.GONE
                    } else {
                        spoken_language.text =
                            getString(R.string.spoken_languages_title) + createLanguagesLine(
                                response.body()!!.spokenLanguages
                            )
                    }
                    if (response.body()!!.overview.isEmpty()) {
                        overview.visibility = View.GONE
                    } else {
                        overview.text =
                            response.body()!!.overview
                    }
                    if (response.body()!!.productionCompanies.isEmpty()) {
                        companies.visibility = View.GONE
                    } else {
                        companies.text =
                            getString(R.string.production_companies_title) + createCompaniesLine(
                                response.body()!!.productionCompanies
                            )
                    }
                    if (response.body()!!.budget == 0) {
                        budget.visibility = View.GONE
                    } else {
                        budget.text =
                            getString(R.string.budget_title) + "${response.body()!!.budget}$"
                    }
                    if (response.body()!!.revenue == 0) {
                        revenue.visibility = View.GONE
                    } else {
                        revenue.text =
                            getString(R.string.revenue_title) + "${response.body()!!.revenue}$"
                    }

                } catch (e: Exception) {
                    Log.e("Movie Mistake", "There is a mistake with downloading the movie.")
                }
            }
        }
    }

    private fun uploadPicture(imageView: ImageView, URI: String) {
        Glide.with(requireContext())
            .load(Uri.parse("${MainScreenAdapter.BASE_URI_FOR_IMAGES}${URI}"))
            .centerCrop()
            .into(imageView)
    }


    private fun getYearFromDate(date: String): String {
        val yearMonthDay = date.split(dashSeparator)
        return yearMonthDay[0]
    }

    private fun convertDataToRightFormat(data: String): String {
        return data.split(dashSeparator).reversed().joinToString(dotSeparator)
    }

    private fun createGenresLine(list: List<GenresResponse>): String {
        var line = ""
        for (i in list.indices) {
            line += if (i == 0) list[i].name
            else ", ${list[i].name}"
        }
        return line
    }

    private fun createCountriesLine(list: List<ProductionCountriesResponse>): String {
        var line = ""
        for (i in list.indices) {
            line += if (i == 0) list[i].name
            else ", ${list[i].name}"
        }
        return line
    }

    private fun createLanguagesLine(list: List<SpokenLanguagesResponse>): String {
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
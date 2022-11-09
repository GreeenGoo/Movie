package com.education.movie.presentation.fragments.movie

import android.annotation.SuppressLint
import android.net.Uri
import android.opengl.Visibility
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
import com.education.movie.presentation.viewmodel.mainscreen.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.background_poster
import kotlinx.android.synthetic.main.fragment_movie.budget
import kotlinx.android.synthetic.main.fragment_movie.companies
import kotlinx.android.synthetic.main.fragment_movie.homepage
import kotlinx.android.synthetic.main.fragment_movie.movie_genres
import kotlinx.android.synthetic.main.fragment_movie.overview
import kotlinx.android.synthetic.main.fragment_movie.poster
import kotlinx.android.synthetic.main.fragment_movie.production_countries
import kotlinx.android.synthetic.main.fragment_movie.release_date
import kotlinx.android.synthetic.main.fragment_movie.revenue
import kotlinx.android.synthetic.main.fragment_movie.runtime
import kotlinx.android.synthetic.main.fragment_movie.spoken_language
import kotlinx.android.synthetic.main.fragment_movie.tagline
import kotlinx.android.synthetic.main.fragment_movie.title_and_release_date
import kotlinx.android.synthetic.main.fragment_movie.vote

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundleNote: Int? = arguments?.getInt(MainScreenViewModel.BUNDLE_KEY)
        bundleNote?.let { id ->
            viewModel.getMovie(id)
            viewModel.movie.observe(viewLifecycleOwner) { response ->
                try {
                    uploadPicture(background_poster, response.body()!!.backdropPath)
                    title_and_release_date.text =
                        "${response.body()?.title} (${getYearFromDate(response.body()?.releaseDate.toString())})"
                    uploadPicture(poster, response.body()!!.posterPath)
                    tagline.text = response.body()!!.tagline
                    release_date.text = convertDataToRightFormat(response.body()!!.releaseDate)
                    movie_genres.text = createGenresLine(response.body()!!.genres)
                    runtime.text =
                        "${response.body()!!.runtime / 60} h ${response.body()!!.runtime % 60} min"
                    vote.text = "${response.body()!!.voteAverage} (${response.body()!!.voteCount})"
                    homepage.text = response.body()!!.homepage
                    production_countries.text =
                        createCountriesLine(response.body()!!.productionCountries)
                    spoken_language.text = createLanguagesLine(response.body()!!.spokenLanguages)
                    overview.text = response.body()!!.overview
                    companies.text = createCompaniesLine(response.body()!!.productionCompanies)
                    if (response.body()!!.budget == 0)
                        budget.visibility = View.GONE
                    budget.text = "${response.body()!!.budget}$"
                    revenue.text = "${response.body()!!.revenue}$"
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
        val yearMonthDay = date.split("-")
        return yearMonthDay[0]
    }

    private fun convertDataToRightFormat(data: String): String {
        return data.split("-").reversed().joinToString(".")
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

//    private fun viewVisibility (view : View, visibility : Int){
//        if ()
//    }
}
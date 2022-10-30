package com.education.movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.education.movie.R
import com.education.movie.presentation.fragments.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MoviesFragment())
            .commit()
    }
}
package com.education.movie.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.education.movie.R
import com.education.movie.presentation.fragments.MoviesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MoviesFragment())
            .commit()
    }
}
package com.education.movie.presentation

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.education.movie.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        setupActionBarWithNavController(navController)
        supportActionBar?.hide()

    }

//    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp() || findNavController(R.id.fragment_container).navigateUp()
//    }
}
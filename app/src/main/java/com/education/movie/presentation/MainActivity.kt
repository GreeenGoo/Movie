package com.education.movie.presentation

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.education.movie.R
import com.education.movie.presentation.fragments.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.recycler_view

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MoviesFragment())
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            "StoreRecyclerView",
            recycler_view.layoutManager?.onSaveInstanceState()
        )
        Log.d("State", "Save an instance state")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        mLayoutManagerState = savedInstanceState.getParcelable("StoreRecyclerView")
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("State", "Restore an instance state")
    }

    override fun onResume() {
        super.onResume()

        if (mLayoutManagerState != null) {
            recycler_view.layoutManager?.onRestoreInstanceState(mLayoutManagerState)
        }
        Log.d("State", "!!!Restoring an instance state!!!")
    }

    companion object {
        var mLayoutManagerState: Parcelable? = null
    }
}
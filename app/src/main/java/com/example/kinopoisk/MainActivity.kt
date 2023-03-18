package com.example.kinopoisk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoisk.R
import com.example.kinopoisk.bookmarksFragment.BookmarksFragment
import com.example.kinopoisk.bookmarksFragment.BookmarksViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val bookmarksViewModel: BookmarksViewModel = BookmarksViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigateMenuInit()
        bookmarksViewModel.initDatabase(this)
    }


    private fun bottomNavigateMenuInit() {

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navigation_main)
        return navController.navigateUp()
    }
}
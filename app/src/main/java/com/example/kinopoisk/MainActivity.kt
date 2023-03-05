package com.example.kinopoisk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoisk.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigateMenuInit()
    }

    // setting bottom navigation menu
    private fun bottomNavigateMenuInit() {
        //нахожу view для BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        //получаю контроллер нашего фрагмента
        val navController = findNavController(R.id.fragmentContainerView)
        //записываю в нижнее меню наш контроллер
        bottomNavigationView.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navigation_main)
        return navController.navigateUp()
    }
}
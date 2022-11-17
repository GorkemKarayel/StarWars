package com.karayel.starwars.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.karayel.starwars.core.Welcome
import com.karayel.starwars.screens.movielist.MovieListScreen
import com.karayel.starwars.screens.movielist.MovieListViewModel
import com.karayel.starwars.screens.welcome.WelcomeScreen
import com.karayel.starwars.ui.theme.StarWarsTheme

/*
    Activity Lifecycle = https://developer.android.com/guide/components/activities/activity-lifecycle
 */
@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {

    private val movieListViewModel by viewModels<MovieListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // https://www.jetpackcompose.app/What-is-the-equivalent-of-FrameLayout-in-Jetpack-Compose
        Log.d("Scope", "onCreate")
        setContent {
            StarWarsTheme {
                OnBecomeVisibleScreen()
            }
        }
    }

    @Composable
    fun OnBecomeVisibleScreen() {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(navController = navController, startDestination = Welcome.route) {

                composable(
                    route = Welcome.route
                ) {
                    WelcomeScreen(navController = navController)
                }

                composable(
                    route = "MovieList?jediName={jediName}",
                    arguments = listOf(navArgument("jediName") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) { backStackEntry ->
                    MovieListScreen(
                        viewModel = movieListViewModel,
                        jediName = backStackEntry.arguments?.getString("jediName").orEmpty(),
                        navController = navController
                    )
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Scope", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Scope", "onResume")
        Log.d("Scope", "================")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Scope", "onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("life", "Hello there")
        super.onSaveInstanceState(outState)
        Log.d("Scope", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val survivedValue = savedInstanceState.getString("life")
        Log.d("Scope", "onSaveInstanceState = $survivedValue")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Scope", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Scope", "onDestroy")
    }
}

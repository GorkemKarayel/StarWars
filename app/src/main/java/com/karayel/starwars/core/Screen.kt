package com.karayel.starwars.core

sealed class Screen {
    abstract val route: String
}

object Welcome: Screen() {
    override val route: String
        get() = "Welcome"
}

data class MovieList(val jediName: String): Screen() {
    override val route: String
        get() = "MovieList?jediName=$jediName"
}

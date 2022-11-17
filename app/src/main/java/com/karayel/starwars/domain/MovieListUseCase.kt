package com.karayel.starwars.domain

import com.karayel.starwars.model.Movie

object MovieListUseCase {

    fun createMovieList(): List<Movie> {
        val movieList = mutableListOf<Movie>()
        // Scope functions
        // https://medium.com/mobile-app-development-publication/mastering-kotlin-standard-functions-run-with-let-also-and-apply-9cd334b0ef84

        movieList.apply {
            add(
                Movie(
                    name = "Episode IV - A New Hope",
                    date = "1977",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/tr/4/4a/Episode4.jpg",
                )
            )
            add(
                Movie(
                    name = "The Empire Strikes Back",
                    date = "1978",
                    imageUrl = "https://flxt.tmsimg.com/assets/p8884_p_v8_ae.jpg",
                )
            )
            add(
                Movie(
                    name = "Star Wars Episode VI - Return of the Jedi",
                    date = "1983",
                    imageUrl = "https://turkcealtyazi.org/images/poster/0086190.jpg",
                )
            )
            add(
                Movie(
                    name = "Star Wars: Episode I - The Phantom Menace",
                    date = "1999",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/tr/7/7a/Ep1.jpg",
                )
            )
            add(
                Movie(
                    name = "Star Wars: Episode II - Attack of the Clones",
                    date = "2002",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/tr/0/03/450px-Star_Wars_Attack_of_the_Clones_poster.jpg",
                )
            )
            add(
                Movie(
                    name = "Star Wars: Episode III - Revenge of the Sith",
                    date = "2005",
                    imageUrl = "https://tr.web.img3.acsta.net/pictures/bzp/01/40623.jpg",
                )
            )
            add(
                Movie(
                    name = "Star Wars: The Force Awakens",
                    date = "2015",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/tr/2/2f/Star_Wars_G%C3%BC%C3%A7_Uyan%C4%B1yor_afi%C5%9F.jpg",
                )
            )
            add(
                Movie(
                    name = "Rogue One: A Star Wars Story",
                    date = "2016",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/tr/e/ea/Rogue_One_-_Bir_Star_Wars_Hikayesi.jpg",
                )
            )
            add(
                Movie(
                    name = "Star Wars:The Last Jedi",
                    date = "2017",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/tr/5/55/Star_Wars_Son_Jedi_afi%C5%9F.jpg",
                )
            )
            add(
                Movie(
                    name = "Star Wars: The Rise of Skywalker",
                    date = "2019",
                    imageUrl = "https://tr.web.img4.acsta.net/pictures/19/10/30/13/52/0959246.jpg",
                )
            )
        }
        return movieList
    }
}

class DummyMovieListUseCase
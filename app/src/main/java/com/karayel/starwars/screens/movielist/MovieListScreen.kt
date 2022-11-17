package com.karayel.starwars.screens.movielist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.karayel.starwars.R
import com.karayel.starwars.model.Movie
import com.karayel.starwars.screens.movielist.Direction.*

@ExperimentalFoundationApi
@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    jediName: String,
    viewModel: MovieListViewModel,
    navController: NavController
) {

    val movieListFlow by viewModel.movieListFlow.collectAsState()
    val loadingStateFlow by viewModel.loadingStateFlow.collectAsState()
    val sortState = rememberSaveable { mutableStateOf(ASC) }
    val listState = rememberLazyListState()

    Scaffold(
        modifier = modifier,
        scaffoldState = rememberScaffoldState(),
        topBar = {
            TopAppBar(jediName = jediName) {
                navController.popBackStack()
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton {
                if (sortState.value == ASC) sortState.value = DESC else sortState.value = ASC
                viewModel.sortMovie(sortState.value)
            }
        },
        content = { padding ->
            if (loadingStateFlow) {
                Loading(modifier)
            }
            LazyColumn(
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = listState,
            ) {
                itemsIndexed(items = movieListFlow, key = { _, item -> item.date }) { _, movie ->
                    // https://developer.android.com/jetpack/compose/lists#item-animations
                    Row(Modifier.animateItemPlacement()) {
                        MovieCard(movie = movie)
                    }
                }
            }
        }
    )
}

@Composable
fun Loading(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(36.dp),
            color = Color.DarkGray
        )
    }
}

@Composable
fun TopAppBar(jediName: String, onBackClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.movie_list_top_bar_name, jediName))
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clickable { onBackClicked() },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.DarkGray
            )
        },
        backgroundColor = Color.LightGray
    )
}

@Composable
fun FloatingActionButton(onClicked: () -> Unit) {
    FloatingActionButton(onClick = { onClicked() }) {
        Text("<>")
    }
}

@Composable
fun MovieCard(movie: Movie) = with(movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(124.dp)
            .padding(horizontal = 12.dp)
            .clickable { },
        shape = RoundedCornerShape(16),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 80.dp, height = 80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = name,
                    modifier = Modifier.padding(top = 2.dp),
                    color = Color.DarkGray
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = date,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Preview
@Composable
fun FloatingActionButtonPreview() {

}

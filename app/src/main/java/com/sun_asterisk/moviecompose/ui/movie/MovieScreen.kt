package com.sun_asterisk.moviecompose.ui.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.palette.BitmapPalette
import com.sun_asterisk.moviecompose.extensions.paging
import com.sun_asterisk.moviecompose.mock.movie
import com.sun_asterisk.moviecompose.mock.movie1
import com.sun_asterisk.moviecompose.mock.movie2
import com.sun_asterisk.moviecompose.models.entities.Movie
import com.sun_asterisk.moviecompose.models.network.NetworkState
import com.sun_asterisk.moviecompose.models.network.onLoading
import com.sun_asterisk.moviecompose.network.Api
import com.sun_asterisk.moviecompose.network.compose.NetworkImage
import com.sun_asterisk.moviecompose.ui.main.MainScreenHomeTab
import com.sun_asterisk.moviecompose.ui.main.MainViewModel
import com.sun_asterisk.moviecompose.ui.theme.MovieComposeTheme
import com.sun_asterisk.moviecompose.ui.theme.white87

@ExperimentalPagerApi
@Composable
fun MovieScreen(
    viewModel: MainViewModel,
    selectPoster: (MainScreenHomeTab, Long) -> Unit,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    val networkState: NetworkState by viewModel.movieLoadingState
    val movies by viewModel.movies
    Column {
        MovieBanner()
        Text(
            text = "Phim đang chiếu",
            color = white87,
            textAlign = TextAlign.Start,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 10.dp)
        )
        LazyRow(
            state = lazyListState,
            modifier = modifier
                .statusBarsPadding()
        ) {

            paging(
                items = movies,
                currentIndexFlow = viewModel.moviePageStateFlow,
                fetch = { viewModel.fetchNextMoviePage() }
            ) {
                MoviePlayingList(
                    movie = it,
                    selectPoster = selectPoster
                )
//            MovieListDivider()
            }
        }
    }
    networkState.onLoading {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

/**
 * Full-width divider with padding for [MoviePlayingList]
 */
@Composable
private fun MovieListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@ExperimentalPagerApi
@Composable
fun MovieBanner() {
    val listUrl = listOf(
        "https://arealnews.com/wp-content/uploads/2021/11/Spiderman-No-Way-Home-Wiki.jpg",
        "https://www.elpozo-king.com/hubfs/spiderman-way-home-fecha-estreno-1.jpeg",
        "https://arealnews.com/wp-content/uploads/2021/11/Spiderman-No-Way-Home-Wiki.jpg"
    )
    ConstraintLayout(Modifier.fillMaxWidth().height(200.dp)) {
        val (banner, indicator) = createRefs()
        val pagerState = rememberPagerState()
        HorizontalPager(
            count = listUrl.size,
            state = pagerState,
            modifier = Modifier.constrainAs(banner) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        ) { page ->
            // Our page content
            NetworkImage(
                networkUrl = listUrl[page],
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(indicator) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
    }

}

@Composable
fun MoviePlayingList(
    movie: Movie,
    selectPoster: (MainScreenHomeTab, Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    selectPoster(MainScreenHomeTab.MOVIE, movie.id)
                }
            )
            .padding(start = 16.dp, top = 16.dp),
//        color = MaterialTheme.colors.onBackground
    ) {

        ConstraintLayout(
            modifier = modifier.width(140.dp)
        ) {
            val (image, title) = createRefs()

            var palette by remember { mutableStateOf<Palette?>(null) }
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = modifier
                    .size(140.dp, 220.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    },
            ) {
                NetworkImage(
                    networkUrl = Api.getPosterPath(movie.poster_path),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    bitmapPalette = BitmapPalette {
                        palette = it
                    }
                )
            }
//            Crossfade(
//                targetState = palette,
//                modifier = Modifier
//                    .height(50.dp)
//                    .constrainAs(box) {
//                        top.linkTo(image.bottom)
//                        bottom.linkTo(parent.bottom)
//                    }
//            ) {
//
//                Box(
//                    modifier = Modifier
//                        .background(Color(it?.darkVibrantSwatch?.rgb ?: 0))
//                        .alpha(0.7f)
//                        .fillMaxSize()
//                )
//            }

            Text(
                text = movie.title,
//                style = MaterialTheme.typography.body2,
                color = white87,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.85f)
                    .constrainAs(title) {
                        top.linkTo(image.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(top = 10.dp, bottom = 10.dp)
            )
        }
    }
}

@Preview("Movie Poster")
@Composable
fun PreviewMoviePosterCard(
    @PreviewParameter(PostPreviewParameterProvider::class, limit = 1) movie: Movie
) {
    MovieComposeTheme {
        Surface {
            MoviePlayingList(movie, { _, _ ->
            }, modifier = Modifier)
        }
    }
}

/**
 * Provides sample [Movie] instances for Composable Previews.
 *
 * When creating a Composable Preview using @Preview, you can pass sample data
 * by annotating a parameter with @PreviewParameter:
 *
 * ```
 * @Preview
 * @Composable
 * fun MyPreview(@PreviewParameter(PostPreviewParameterProvider::class, limit = 2) post: Post) {
 *   MyComposable(post)
 * }
 * ```
 *
 * In this simple app we just return the hard-coded posts. When the app
 * would be more complex - e.g. retrieving the posts from a server - this would
 * be the right place to instantiate dummy instances.
 */
class PostPreviewParameterProvider : PreviewParameterProvider<Movie> {
    override val values = sequenceOf(
        movie, movie1, movie2
    )
}

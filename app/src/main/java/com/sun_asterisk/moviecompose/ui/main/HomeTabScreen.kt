package com.sun_asterisk.moviecompose.ui.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.sun_asterisk.moviecompose.ui.movie.MovieScreen
import com.sun_asterisk.moviecompose.ui.people.PeopleScreen
import com.sun_asterisk.moviecompose.ui.theme.purple200
import com.sun_asterisk.moviecompose.ui.tv.TvScreen

@Composable
fun HomeTabScreen(
  viewModel: MainViewModel,
  tabStateHolder: HomeTabStateHolder,
  selectItem: (MainScreenHomeTab, Long) -> Unit
) {
  val selectedTab by viewModel.selectedTab
  val tabs = MainScreenHomeTab.values()

  Scaffold(
    backgroundColor = MaterialTheme.colors.primarySurface,
    topBar = { MainAppBar() },
    bottomBar = {

      BottomNavigation(
        backgroundColor = purple200,
        modifier = Modifier
          .navigationBarsHeight(56.dp)
      ) {

        tabs.forEach { tab ->
          BottomNavigationItem(
            icon = { Icon(imageVector = tab.icon, contentDescription = null) },
            label = { Text(text = stringResource(tab.title), color = Color.White) },
            selected = tab == selectedTab,
            onClick = { viewModel.selectTab(tab) },
            selectedContentColor = LocalContentColor.current,
            unselectedContentColor = LocalContentColor.current,
            modifier = Modifier.navigationBarsPadding()
          )
        }
      }
    }
  ) { innerPadding ->
    val modifier = Modifier.padding(innerPadding)

    Crossfade(selectedTab) { destination ->
      when (destination) {
        MainScreenHomeTab.MOVIE -> MovieScreen(
          viewModel,
          selectItem,
          tabStateHolder.movieLazyListState,
          modifier,
        )
        MainScreenHomeTab.TV -> TvScreen(
          viewModel,
          selectItem,
          tabStateHolder.tvLazyListState,
          modifier,
        )
        MainScreenHomeTab.PERSON -> PeopleScreen(
          viewModel,
          selectItem,
          tabStateHolder.peopleLazyListState,
          modifier,
        )
      }
    }
  }
}

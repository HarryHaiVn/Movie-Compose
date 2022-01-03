package com.sun_asterisk.moviecompose.ui.main

import androidx.compose.foundation.lazy.LazyListState

data class HomeTabStateHolder(
  val movieLazyListState: LazyListState,
  val tvLazyListState: LazyListState,
  val peopleLazyListState: LazyListState,
)

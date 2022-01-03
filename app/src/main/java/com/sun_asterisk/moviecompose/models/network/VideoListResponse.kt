package com.sun_asterisk.moviecompose.models.network

import androidx.compose.runtime.Immutable
import com.sun_asterisk.moviecompose.models.NetworkResponseModel
import com.sun_asterisk.moviecompose.models.Video

@Immutable
data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel

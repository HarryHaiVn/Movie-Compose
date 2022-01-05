package com.sun_asterisk.moviecompose.data.models.network

import androidx.compose.runtime.Immutable
import com.sun_asterisk.moviecompose.data.models.NetworkResponseModel
import com.sun_asterisk.moviecompose.data.models.Video

@Immutable
data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel

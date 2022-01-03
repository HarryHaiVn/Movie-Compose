package com.sun_asterisk.moviecompose.models.network

import androidx.compose.runtime.Immutable
import com.sun_asterisk.moviecompose.models.NetworkResponseModel
import com.sun_asterisk.moviecompose.models.Review

@Immutable
class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel

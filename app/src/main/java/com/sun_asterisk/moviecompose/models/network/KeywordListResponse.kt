package com.sun_asterisk.moviecompose.models.network

import androidx.compose.runtime.Immutable
import com.sun_asterisk.moviecompose.models.Keyword
import com.sun_asterisk.moviecompose.models.NetworkResponseModel

@Immutable
data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel

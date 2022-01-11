package com.sun_asterisk.moviecompose.data.models.network

import androidx.compose.runtime.Immutable
import com.sun_asterisk.moviecompose.data.models.BaseResponseModel
import com.sun_asterisk.moviecompose.data.models.Keyword

@Immutable
data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : BaseResponseModel

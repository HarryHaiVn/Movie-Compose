package com.sun_asterisk.moviecompose.data.models.network

import androidx.compose.runtime.Immutable
import com.sun_asterisk.moviecompose.data.models.BaseResponseModel

@Immutable
data class PersonDetail(
  val birthday: String?,
  val known_for_department: String,
  val place_of_birth: String?,
  val also_known_as: List<String>?,
  val biography: String
) : BaseResponseModel

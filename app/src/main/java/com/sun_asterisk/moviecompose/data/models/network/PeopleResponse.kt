package com.sun_asterisk.moviecompose.data.models.network

import androidx.compose.runtime.Immutable
import com.sun_asterisk.moviecompose.data.models.BaseResponseModel
import com.sun_asterisk.moviecompose.data.models.entities.Person

@Immutable
data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_results: Int,
    val total_pages: Int
) : BaseResponseModel

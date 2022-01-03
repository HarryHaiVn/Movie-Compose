package com.sun_asterisk.moviecompose.models.network

import androidx.compose.runtime.Immutable
import com.sun_asterisk.moviecompose.models.NetworkResponseModel
import com.sun_asterisk.moviecompose.models.entities.Person

@Immutable
data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel

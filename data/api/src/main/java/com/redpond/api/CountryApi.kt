package com.redpond.api

import com.apollographql.apollo3.ApolloClient
import com.redpond.CountriesQuery
import com.redpond.fragment.Country
import javax.inject.Inject

class CountryApi @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun fetchCountries(): List<Country> {
        val data = apolloClient.query(CountriesQuery()).execute().data
            ?: throw IllegalStateException("response data is null.")
        return data.countries.map { it.country }
    }
}
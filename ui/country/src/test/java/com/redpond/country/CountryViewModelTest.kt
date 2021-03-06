package com.redpond.country

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.redpond.base.Args.Companion.CODE
import com.redpond.domain.Country
import com.redpond.domain.repository.CountryRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CountryViewModelTest {

    @MockK
    lateinit var countryRepository: CountryRepository

    @RelaxedMockK
    lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInit_success() = runTest {

        val code = "c1"
        val country = Country(code, "name1")

        coEvery { savedStateHandle.get<String>(CODE) } returns code
        coEvery { countryRepository.fetchCountry(code) } returns country

        val viewModel = CountryViewModel(savedStateHandle, countryRepository)

        assertThat(viewModel.uiState.value.country).isEqualTo(country)
        assertThat(viewModel.uiState.value.isLoading).isFalse()
    }
}

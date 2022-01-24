package com.example.bajp_submission2.ui.tvshow

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShowEntities = viewModel.getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.size)
    }
}
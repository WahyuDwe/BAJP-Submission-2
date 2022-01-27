package com.example.bajp_submission2.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.bajp_submission2.R
import com.example.bajp_submission2.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DataDummy.dataDummyMovies()
    private val dummyTvShow = DataDummy.dataDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadDataMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDataDetailMovies() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.iv_detail_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.details_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.details_release_date)).check(matches(withText(dummyMovies[0].date)))
        onView(withId(R.id.detail_score)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_score)).check(matches(withText(dummyMovies[0].score)))
        onView(withId(R.id.detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_genre)).check(matches(withText(dummyMovies[0].genre)))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(withText(dummyMovies[0].description)))
    }

    @Test
    fun loadDataTvShow() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailDataTvShow() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.iv_detail_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.details_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.details_release_date)).check(matches(withText(dummyTvShow[0].date)))
        onView(withId(R.id.detail_score)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_score)).check(matches(withText(dummyTvShow[0].score)))
        onView(withId(R.id.detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_genre)).check(matches(withText(dummyTvShow[0].genre)))
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_description)).check(matches(withText(dummyTvShow[0].overview)))
    }
}
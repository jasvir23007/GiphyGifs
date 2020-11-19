package com.jasvir.freshworks.ui.favourite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.jasvir.freshworks.R
import com.jasvir.freshworks.ui.search.SearchActivity
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FavFragmentTest{

    @get:Rule
    var searchActivityTestRule: ActivityScenarioRule<SearchActivity> =
        ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun test_recycler_view_displayed() {

        onView(allOf(isDisplayed(),withId(R.id.view_pager_home))).perform(ViewActions.swipeLeft())
        onView(
            allOf(
                isDisplayed(),
                withId(R.id.fav_recycler_view)
            )
        )
    }

    @Test
    fun test_fav_added_in_recyclerview() {
        onView(allOf(isDisplayed(), withId(R.id.fragment_recycler_view)))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        onView(allOf(isDisplayed(),withId(R.id.view_pager_home))).perform(ViewActions.swipeLeft())
        onView(
            allOf(
                isDisplayed(),
                withId(R.id.fav_recycler_view)
            )
        )

    }
}
package com.jasvir.freshworks.ui.search

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.jasvir.freshworks.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchFragmentTest {

    @get:Rule
    var searchActivityTestRule: ActivityScenarioRule<SearchActivity> =
        ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun test_editext_displayed() {
        onView(allOf(isDisplayed(), withId(R.id.et_search)))
    }

    @Test
    fun test_search_keyboard_opened() {
        onView(allOf(isDisplayed(), withId(R.id.et_search)))
            .perform(click())
    }


    @Test
    fun test_keyboard_closed() {
        onView(allOf(isDisplayed(), withId(R.id.et_search)))
            .perform(click(), typeText("a"), closeSoftKeyboard())
    }

    @Test
    fun test_recycler_view_displayed() {
        onView(allOf(isDisplayed(), withId(R.id.fragment_recycler_view)))
    }

    @Test
    fun test_edittext_for_search() {
        onView(allOf(isDisplayed(), withId(R.id.et_search))).perform(typeText("something"))

        var activity: Activity? = null

        searchActivityTestRule.scenario.onActivity {
            activity = it
        }

        val recyclerView: RecyclerView = activity!!.findViewById(R.id.fragment_recycler_view)
        onView(allOf(isDisplayed(), withId(R.id.fragment_recycler_view)))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(recyclerView.adapter!!.itemCount - 1))

    }

    @Test
    fun test_recycler_item_click() {
        onView(allOf(isDisplayed(), withId(R.id.fragment_recycler_view)))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }


}
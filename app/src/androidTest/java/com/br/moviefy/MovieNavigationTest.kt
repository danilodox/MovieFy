package com.br.moviefy

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {


    fun testMovieFragmentsNavigation(){

        //Setup
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(ViewMatchers.withId(R.id.recycler_list_movie)).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.fragment_movie_detail_parent)).
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))



    }
}
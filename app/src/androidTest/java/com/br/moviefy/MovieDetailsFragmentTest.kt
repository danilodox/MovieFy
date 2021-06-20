package com.br.moviefy

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.br.moviefy.ui.moviedetails.MovieDetailsFragment
import com.br.moviefy.ui.movies.MoviesFragment
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MovieDetailsFragmentTest {


    @Rule
    var fragmentTestRule: FragmentTestRule<MainActivity, MoviesFragment> = FragmentTestRule(
        MainActivity::class.java,
        MoviesFragment::class.java
    )



    @Test
    fun ensureThatComponentsAreVisible() {

        Espresso.onView(ViewMatchers.withId(R.id.progress_loading_event)).check(
            ViewAssertions.matches(
                Matchers.not(ViewMatchers.isDisplayed())
            )
        )

        /*Espresso.onView(ViewMatchers.withId(R.id.error_network_event)).check(
            ViewAssertions.matches(
                Matchers.not(ViewMatchers.isDisplayed())
            )
        )*/
    }
}
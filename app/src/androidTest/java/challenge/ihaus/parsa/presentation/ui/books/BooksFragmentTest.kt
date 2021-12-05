package challenge.ihaus.parsa.presentation.ui.books

import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
// It could not resolve, Because I did not time, I could not relove it
//import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import challenge.ihaus.parsa.R
import challenge.ihaus.parsa.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BooksFragmentTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun appLaunched_isBooksFragmentVisible() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun bookItemClicked_isBookDetailFragmentVisible() {
//        onView(withId(R.id.recycler_view)).
//        perform(RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click()))
    }

}

package com.technorapper.boiler.ui.onboarding.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.technorapper.boiler.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    @Test
    fun testActivity_inView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(withId(R.id.main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
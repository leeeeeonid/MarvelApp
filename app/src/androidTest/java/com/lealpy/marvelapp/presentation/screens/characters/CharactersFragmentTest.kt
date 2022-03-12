package com.lealpy.marvelapp.presentation.screens.characters

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.data.repositories.TestCharactersRepositoryImpl
import com.lealpy.marvelapp.di.RepositoriesModule
import com.lealpy.marvelapp.di.UseCasesModule
import com.lealpy.marvelapp.di.launchFragmentInHiltContainer
import com.squareup.rx3.idler.Rx3Idler
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(
    RepositoriesModule::class,
    UseCasesModule::class
)
@HiltAndroidTest
class CharactersFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()

        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx3Idler.create("RxJava 3.x IO Scheduler")
        )
    }

    @Test
    fun show_recycler_view_when_CharactersFragment_starts() {
        /** Given */

        /** When */
        launchFragmentInHiltContainer<CharactersFragment>()

        /** Then */
        Espresso.onView(
            withId(R.id.recyclerView)
        ).check(
            ViewAssertions.matches(isDisplayed())
        )
    }

    @Test
    fun check_content_of_recycler_view_items() {
        /** Given */
        val testCharacters = TestCharactersRepositoryImpl.TEST_CHARACTERS

        /** When */
        launchFragmentInHiltContainer<CharactersFragment>()
        Thread.sleep(2000)

        /** Then */
        for (position in 0..testCharacters.lastIndex) {
            Espresso.onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.scrollToPosition<CharacterAdapter.CharacterHolder>(position)
            )

            Espresso.onView(withId(R.id.recyclerView))
                .check(
                    ViewAssertions.matches(
                        RecyclerViewChildActions.childOfViewAtPositionWithMatcher(
                            R.id.characterItemName,
                            position,
                            withText(testCharacters[position].name)
                        )
                    )
                )
                .check(
                    ViewAssertions.matches(
                        RecyclerViewChildActions.childOfViewAtPositionWithMatcher(
                            R.id.characterItemDescription,
                            position,
                            withText(testCharacters[position].description)
                        )
                    )
                )
        }
    }

}

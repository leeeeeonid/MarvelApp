package com.lealpy.marvelapp.presentation.screens.details

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.lealpy.marvelapp.R
import com.lealpy.marvelapp.data.repositories.TestCharactersRepositoryImpl
import com.lealpy.marvelapp.di.RepositoriesModule
import com.lealpy.marvelapp.di.UseCasesModule
import com.lealpy.marvelapp.di.launchFragmentInHiltContainer
import com.lealpy.marvelapp.presentation.utils.Const
import com.squareup.rx3.idler.Rx3Idler
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(
    RepositoriesModule::class,
    UseCasesModule::class
)
@HiltAndroidTest
class DetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()

        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx3Idler.create("RxJava 3.x IO Scheduler")
        )
    }

    @Test
    fun show_character_at_start() {
        /** Given */
        val testCharacter = TestCharactersRepositoryImpl.TEST_CHARACTERS[0]
        val fragmentArgs = bundleOf(Const.CHARACTER_ID_KEY to testCharacter.id)

        /** When */
        launchFragmentInHiltContainer<DetailsFragment>(fragmentArgs = fragmentArgs)

        /** Then */
        Espresso.onView(
            withId(R.id.characterName)
        ).check(
            ViewAssertions.matches(
                withText(testCharacter.name)
            )
        )

        Espresso.onView(
            withId(R.id.characterDescription)
        ).check(
            ViewAssertions.matches(
                withText(testCharacter.description)
            )
        )
    }

}

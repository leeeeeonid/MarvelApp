package com.lealpy.marvelapp.presentation.screens.details

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.use_cases.GetCharacterByIdUseCase
import com.lealpy.marvelapp.getOrAwaitValue
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class DetailsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var detailsViewModel: DetailsViewModel
    private val getCharacterByIdUseCase = Mockito.mock(GetCharacterByIdUseCase::class.java)
    private val savedStateHandle = Mockito.mock(SavedStateHandle::class.java)

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        Mockito.`when`(
            getCharacterByIdUseCase(anyInt())
        ).thenReturn(
            Single.just(TEST_CHARACTER)
        )

        Mockito.`when`(
            savedStateHandle.get<Int>(anyString())
        ).thenReturn(
            TEST_CHARACTER.id
        )

        detailsViewModel = DetailsViewModel(
            savedStateHandle = savedStateHandle,
            getCharacterByIdUseCase = getCharacterByIdUseCase,
        )
    }

    @Test
    fun `get character at start`() {
        /** Given */

        /** When */

        /** Then */
        val expected = TEST_CHARACTER
        val actual = detailsViewModel.character.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `progressBarVisibility is GONE after character loading`() {
        /** Given */

        /** When */

        /** Then */
        val expected = View.GONE
        val actual = detailsViewModel.progressBarVisibility.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    companion object {
        private val TEST_CHARACTER = Character(
            id = 1017100,
            name = "A-Bomb (HAS)",
            description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
            imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
            modified = "2013-09-18T15:54:04-0400"
        )
    }

}
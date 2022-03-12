package com.lealpy.marvelapp.presentation.screens.characters

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.models.SortBy.*
import com.lealpy.marvelapp.domain.use_cases.GetCharactersUseCase
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
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class CharactersViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var charactersViewModel: CharactersViewModel
    private val getCharactersUseCase = Mockito.mock(GetCharactersUseCase::class.java)

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        Mockito.`when`(
            getCharactersUseCase()
        ).thenReturn(
            Single.just(START_TEST_CHARACTERS)
        )

        charactersViewModel = CharactersViewModel(
            getCharactersUseCase = getCharactersUseCase,
        )
    }

    @Test
    fun `get characters at start`() {
        /** Given */

        /** When */

        /** Then */
        val expected = START_TEST_CHARACTERS
        val actual = charactersViewModel.characters.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `get characters when swiped refresh`() {
        /** Given */
        val runtimeTestCharacters = START_TEST_CHARACTERS.reversed()

        Mockito.`when`(
            getCharactersUseCase()
        ).thenReturn(
            Single.just(runtimeTestCharacters)
        )

        /** When */
        charactersViewModel.onSwipedRefresh()

        /** Then */
        val expected = runtimeTestCharacters
        val actual = charactersViewModel.characters.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `get sorted characters when sort by alphabet clicked`() {
        /** Given */

        /** When */
        charactersViewModel.onSortByClicked(BY_ALPHABET)

        /** Then */
        val expected = listOf(
            Character(
                id = 1017100,
                name = "A-Bomb (HAS)",
                description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
                modified = "2013-09-18T15:54:04-0400"
            ),
            Character(
                id = 1009146,
                name = "Abomination (Emil Blonsky)",
                description = "Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg",
                modified = "2012-03-20T12:32:12-0400"
            ),
            Character(
                id = 1010354,
                name = "Adam Warlock",
                description = "Adam Warlock is an artificially created human who was born in a cocoon at a scientific complex called The Beehive.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/a/f0/5202887448860.jpg",
                modified = "2013-08-07T13:49:06-0400"
            ),
        )
        val actual = charactersViewModel.characters.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `get sorted characters when sort by date clicked`() {
        /** Given */

        /** When */
        charactersViewModel.onSortByClicked(BY_DATE)

        /** Then */
        val expected = listOf(
            Character(
                id = 1009146,
                name = "Abomination (Emil Blonsky)",
                description = "Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg",
                modified = "2012-03-20T12:32:12-0400"
            ),
            Character(
                id = 1010354,
                name = "Adam Warlock",
                description = "Adam Warlock is an artificially created human who was born in a cocoon at a scientific complex called The Beehive.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/a/f0/5202887448860.jpg",
                modified = "2013-08-07T13:49:06-0400"
            ),
            Character(
                id = 1017100,
                name = "A-Bomb (HAS)",
                description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
                modified = "2013-09-18T15:54:04-0400"
            ),
        )
        val actual = charactersViewModel.characters.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `get sorted characters when sort by alphabet descending clicked`() {
        /** Given */

        /** When */
        charactersViewModel.onSortByClicked(BY_ALPHABET_DESCENDING)

        /** Then */
        val expected = listOf(
            Character(
                id = 1010354,
                name = "Adam Warlock",
                description = "Adam Warlock is an artificially created human who was born in a cocoon at a scientific complex called The Beehive.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/a/f0/5202887448860.jpg",
                modified = "2013-08-07T13:49:06-0400"
            ),
            Character(
                id = 1009146,
                name = "Abomination (Emil Blonsky)",
                description = "Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg",
                modified = "2012-03-20T12:32:12-0400"
            ),
            Character(
                id = 1017100,
                name = "A-Bomb (HAS)",
                description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
                modified = "2013-09-18T15:54:04-0400"
            ),
        )
        val actual = charactersViewModel.characters.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `get sorted characters when sort by date descending clicked`() {
        /** Given */

        /** When */
        charactersViewModel.onSortByClicked(BY_DATE_DESCENDING)

        /** Then */
        val expected = listOf(
            Character(
                id = 1017100,
                name = "A-Bomb (HAS)",
                description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
                modified = "2013-09-18T15:54:04-0400"
            ),
            Character(
                id = 1010354,
                name = "Adam Warlock",
                description = "Adam Warlock is an artificially created human who was born in a cocoon at a scientific complex called The Beehive.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/a/f0/5202887448860.jpg",
                modified = "2013-08-07T13:49:06-0400"
            ),
            Character(
                id = 1009146,
                name = "Abomination (Emil Blonsky)",
                description = "Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg",
                modified = "2012-03-20T12:32:12-0400"
            ),
        )
        val actual = charactersViewModel.characters.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `progressBarVisibility is GONE after character loading`() {
        /** Given */

        /** When */

        /** Then */
        val expected = View.GONE
        val actual = charactersViewModel.progressBarVisibility.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    companion object {
        private val START_TEST_CHARACTERS = listOf(
            Character(
                id = 1009146,
                name = "Abomination (Emil Blonsky)",
                description = "Formerly known as Emil Blonsky, a spy of Soviet Yugoslavian origin working for the KGB, the Abomination gained his powers after receiving a dose of gamma radiation similar to that which transformed Bruce Banner into the incredible Hulk.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg",
                modified = "2012-03-20T12:32:12-0400"
            ),
            Character(
                id = 1017100,
                name = "A-Bomb (HAS)",
                description = "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
                modified = "2013-09-18T15:54:04-0400"
            ),
            Character(
                id = 1010354,
                name = "Adam Warlock",
                description = "Adam Warlock is an artificially created human who was born in a cocoon at a scientific complex called The Beehive.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/a/f0/5202887448860.jpg",
                modified = "2013-08-07T13:49:06-0400"
            ),
        )
    }

}

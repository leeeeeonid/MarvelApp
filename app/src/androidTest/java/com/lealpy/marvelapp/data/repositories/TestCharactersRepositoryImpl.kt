package com.lealpy.marvelapp.data.repositories

import com.lealpy.marvelapp.domain.models.Character
import com.lealpy.marvelapp.domain.repositories.CharactersRepository
import io.reactivex.rxjava3.core.Single

class TestCharactersRepositoryImpl : CharactersRepository {
    override fun getCharacters(): Single<List<Character>> {
        return Single.just(TEST_CHARACTERS)
    }

    override fun getCharacterById(characterId: Int): Single<Character> {
        return Single.just(TEST_CHARACTERS[0])
    }

    companion object {
        val TEST_CHARACTERS = listOf(
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
            Character(
                id = 1010905,
                name = "Amun",
                description = "Amun is a ruthless teenage assassin, employed by the Sisterhood of the Wasp to serve under the mage Vincent after Araña interrupted the ritual to initiate the Wasp's new chosen one.",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
                modified = "1969-12-31T19:00:00-0500"
            ),
            Character(
                id = 1011684,
                name = "Angel (Angel Salvadore)",
                description = "Driven out of home by her abusive step-father, fourteen-year old Angel Salvadore slept in the woods, where her mutant nature manifested itself in the form of a protective cocoon",
                imageURL = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
                modified = "2021-08-19T00:06:46-0400"
            ),
        )
    }

}

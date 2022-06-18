package co.pokeapi.restpoke.api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

internal class PokemonApiTest {

    @InjectMocks
    lateinit var pokemonApi: PokemonApi

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        pokemonApi.pokemonApiUrl = ""
    }

    @Test
    fun `should throw exception when URI is null`() {
        assertThrows(IllegalArgumentException::class.java) {
            pokemonApi.getPokemon()
        }.let {
            assertEquals("URI is not absolute", it.message)
        }
    }

}
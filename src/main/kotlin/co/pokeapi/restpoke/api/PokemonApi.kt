package co.pokeapi.restpoke.api

import co.pokeapi.restpoke.domain.mapper.PokemonResults
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PokemonApi {

    @Value("\${pokemon-url}")
    lateinit var pokemonApiUrl: String

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(PokemonApi::class.java)
    }

    fun getPokemon() =
        try {
            LOGGER.info("PokemonApi.getPokemon() -- Start -- Getting list of pokemon from PokeAPI")

            val result = RestTemplate().getForObject(pokemonApiUrl, PokemonResults::class.java)!!

            LOGGER.info(
                "PokemonApi.getPokemon() -- End -- " +
                    "Successfully retrieved list of pokemon from PokeAPI -- " +
                    "result: ${result.count}"
            )

            result
        } catch (e: Exception) {
            LOGGER.error(
                "PokemonApi.getPokemon() -- Error -- " +
                    "Error retrieving list of pokemon from PokeAPI -- error: ${e.message}"
            )

            throw e
        }
}

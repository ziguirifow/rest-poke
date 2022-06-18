package co.pokeapi.restpoke.services.impl

import co.pokeapi.restpoke.api.PokemonApi
import co.pokeapi.restpoke.domain.dtos.HighlightResult
import co.pokeapi.restpoke.domain.dtos.Pokemon
import co.pokeapi.restpoke.domain.enums.Order
import co.pokeapi.restpoke.domain.exceptions.PokemonNotFoundException
import co.pokeapi.restpoke.services.PokemonService
import co.pokeapi.restpoke.utils.SortUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PokemonServiceImpl(
    private val pokemonApi: PokemonApi,
    private val sortUtil: SortUtil
) : PokemonService {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(PokemonServiceImpl::class.java)
    }

    override fun getPokemon(query: String, sort: Order?): Pokemon =
        try {
            LOGGER.info(
             "PokemonServiceImpl.getPokemon() -- Start -- " +
             "Getting pokemon with query: $query ${if (sort != null) "and sort: $sort" else ""}"
            )

            val mappedPokemons = getMappedPokemons(query)
            val sortedPokemons = sortUtil.sort(mappedPokemons.result, sort)

            val pokemons = isPokemonListEmpty(sortedPokemons)

            LOGGER.info(
             "PokemonServiceImpl.getPokemon() -- End -- " +
             "Successfully retrieved ${pokemons.result.size} pokemons with query: $query ${if (sort != null) "and sort: $sort" else ""}"
            )

            pokemons
        } catch (e: Exception) {
            LOGGER.error(
             "PokemonServiceImpl.getPokemon() -- Error -- " +
             "Error retrieving pokemon with query: $query ${if (sort != null) "and sort: $sort" else ""} " +
             "-- error: ${e.message}"
            )

            throw e
        }

    override fun getPokemonHighlight(query: String, sort: Order?): HighlightResult =
        try {
            LOGGER.info(
             "PokemonServiceImpl.getPokemonHighlight() -- Start -- " +
             "Getting highlighted pokemon with query: $query ${if (sort != null) "and sort: $sort" else ""}"
            )

            val mappedPokemons = getMappedPokemons(query)
            val sortedPokemons = sortUtil.sort(mappedPokemons.result, sort)

            val pokemons = isPokemonListEmpty(sortedPokemons)

            LOGGER.info(
             "PokemonServiceImpl.getPokemonHighlight() -- End -- " +
             "Successfully retrieved ${pokemons.result.size} highlighted pokemon with query: $query ${if (sort != null) "and sort: $sort" else ""}"
            )

            pokemons.highlight(query)
        } catch (e: Exception) {
            LOGGER.error(
             "PokemonServiceImpl.getPokemonHighlight() -- Error -- " +
             "Error retrieving highlighted pokemon with query: $query ${if (sort != null) "and sort: $sort" else ""} " +
             "-- error: ${e.message}"
            )

            throw e
        }

    private fun getMappedPokemons(query: String) = Pokemon(
        pokemonApi.getPokemon().results.filter {
            it.name.contains(query, true)
        }.map { it.name }
    )

    private fun isPokemonListEmpty(sortedPokemons: Pokemon) =
        if (sortedPokemons.result.isEmpty()) {
            throw PokemonNotFoundException()
        } else sortedPokemons

}

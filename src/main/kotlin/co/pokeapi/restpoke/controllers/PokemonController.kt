package co.pokeapi.restpoke.controllers

import co.pokeapi.restpoke.domain.dtos.HighlightResult
import co.pokeapi.restpoke.domain.dtos.Pokemon
import co.pokeapi.restpoke.domain.enums.Order
import co.pokeapi.restpoke.services.PokemonService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.BindException

@RestController
@RequestMapping("pokemons")
class PokemonController(
    private val pokemonService: PokemonService
) {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(PokemonController::class.java)
    }

    @GetMapping
    fun getPokemon(
        @RequestParam(value = "query") query: String,
        @RequestParam(value = "sort") sort: Order?,
    ): ResponseEntity<Pokemon> = try {
        LOGGER.info(
            "PokemonController.getPokemon() -- Start -- " +
                    "Called with query: $query ${if (sort != null) "and sort: $sort" else ""}"
        )

        isQueryBlank(query)

        val result = pokemonService.getPokemon(query, sort)
        val response = ResponseEntity.ok(result)

        LOGGER.info(
            "PokemonController.getPokemon() -- End -- " +
                    "Successfully returned response: ${response.statusCode}"
        )

        response
    } catch (e: Exception) {
        LOGGER.error("PokemonController.getPokemon() -- End -- Error: ${e.message}")

        throw e
    }

    @GetMapping("highlight")
    fun getPokemonHighlight(
        @RequestParam(value = "query") query: String,
        @RequestParam(value = "sort") sort: Order?
    ): ResponseEntity<HighlightResult> = try {
        LOGGER.info(
            "PokemonController.getPokemonHighlight() -- Start -- " +
                    "Called with query: $query ${if (sort != null) "and sort: $sort" else ""}"
        )

        isQueryBlank(query)

        val result = pokemonService.getPokemonHighlight(query, sort)
        val response = ResponseEntity.ok(result)

        LOGGER.info(
            "PokemonController.getPokemonHighlight() -- End -- " +
                    "Successfully returned response: ${response.statusCode}"
        )

        response
    } catch (e: Exception) {
        LOGGER.error("PokemonController.getPokemonHighlight() -- End -- Error: ${e.message}")

        throw e
    }

    private fun isQueryBlank(query: String) {
        if (query.isBlank()) {
            throw BindException("Query param is required and cannot be blank")
        }
    }
}
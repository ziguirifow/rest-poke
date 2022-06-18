package co.pokeapi.restpoke.services

import co.pokeapi.restpoke.domain.dtos.HighlightResult
import co.pokeapi.restpoke.domain.dtos.Pokemon
import co.pokeapi.restpoke.domain.enums.Order
import org.springframework.stereotype.Component

@Component
interface PokemonService {
    fun getPokemon(query: String, sort: Order?): Pokemon
    fun getPokemonHighlight(query: String, sort: Order?): HighlightResult
}
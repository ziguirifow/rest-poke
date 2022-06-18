package co.pokeapi.restpoke.domain.mapper

data class PokemonResults(
    val count: Int,
    val results: List<Results>
)

data class Results(
    val name: String,
    val url: String
)

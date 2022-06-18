package co.pokeapi.restpoke.domain.exceptions

class PokemonNotFoundException : RuntimeException() {
    override val message: String
        get() = "Pokemon not found"
}
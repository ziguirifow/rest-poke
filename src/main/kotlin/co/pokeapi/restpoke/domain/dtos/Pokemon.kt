package co.pokeapi.restpoke.domain.dtos

data class Pokemon(
    val result: List<String>
) {
    fun highlight(query: String) = HighlightResult(
        result.map {
            Highlight(
                name = it,
                highlight = it.replace(query, "<pre>${query.lowercase()}</pre>", true)
            )
        }
    )
}
package co.pokeapi.restpoke.integration

import co.pokeapi.restpoke.domain.enums.Order
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
internal class RestPokeApplicationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @ParameterizedTest
    @CsvFileSource(resources = ["/get-pokemons-test.csv"], numLinesToSkip = 1, delimiter = ';')
    fun `should return a pokemon`(query: String?, order: Order?, status: Int, strict: Boolean, expected: String) {

        val requestBuilder =
            MockMvcRequestBuilders
                .get("/pokemons")
                .queryParam("query", query)
                .queryParam("sort", order?.name)

        mockMvc.perform(requestBuilder)
            .andExpect(status().`is`(status))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expected, strict))
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/highlight-pokemon-test.csv"], numLinesToSkip = 1, delimiter = ';')
    fun `should highlight a pokemon`(query: String?, order: Order?, status: Int, strict: Boolean, expected: String) {

        val requestBuilder =
            MockMvcRequestBuilders
                .get("/pokemons/highlight")
                .queryParam("query", query)
                .queryParam("sort", order?.name)
                .queryParam("highlight", "true")

        mockMvc.perform(requestBuilder)
            .andExpect(status().`is`(status))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expected, strict))
    }
}
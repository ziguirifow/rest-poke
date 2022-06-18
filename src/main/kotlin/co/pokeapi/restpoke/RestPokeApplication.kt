package co.pokeapi.restpoke

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class RestPokeApplication

fun main(args: Array<String>) {
	runApplication<RestPokeApplication>(*args)
}

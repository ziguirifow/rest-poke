package co.pokeapi.restpoke.controllers.handler

import co.pokeapi.restpoke.controllers.response.ExceptionResponse
import co.pokeapi.restpoke.domain.exceptions.PokemonNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.net.BindException

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(Exception::class)
    final fun handleAllExceptions(ex: Exception) = run {
        LOGGER.error("CustomExceptionHandler.handleAllExceptions: $ex")
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ExceptionResponse(
                code = HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                message = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                details = setOf(ex.message)
            )
        )
    }

    @ExceptionHandler(BindException::class)
    final fun handleBindException(ex: BindException) = run {
        LOGGER.error("CustomExceptionHandler.handleBindException: $ex")
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionResponse(
                code = HttpStatus.BAD_REQUEST.toString(),
                message = HttpStatus.BAD_REQUEST.reasonPhrase,
                details = setOf(ex.message)
            )
        )
    }

    @ExceptionHandler(PokemonNotFoundException::class)
    final fun handlePokemonNotFoundException(ex: PokemonNotFoundException) = run {
        LOGGER.error("CustomExceptionHandler.handlePokemonNotFoundException: $ex")
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ExceptionResponse(
                code = HttpStatus.NOT_FOUND.toString(),
                message = HttpStatus.NOT_FOUND.reasonPhrase,
                details = setOf(ex.message)
            )
        )
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CustomExceptionHandler::class.java)
    }
}
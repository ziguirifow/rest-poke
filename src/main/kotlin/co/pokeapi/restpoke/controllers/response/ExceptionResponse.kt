package co.pokeapi.restpoke.controllers.response

import java.time.LocalDateTime

data class ExceptionResponse(

    val code: String?,
    val message: String?,
    val timeStamp: LocalDateTime? = LocalDateTime.now(),
    val details: Set<String?>
)
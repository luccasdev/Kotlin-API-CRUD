package dev.luccas.demo.exception


import org.springframework.http.HttpStatus

class CustomException(override val message: String, val httpStatus: HttpStatus) : RuntimeException()

package com.kukot.credentialsencryption

import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorMessageControllerAdvice {

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleValidationnMessage(exception: MethodArgumentNotValidException): ResponseEntity<*> {
        val errorResponse = exception.bindingResult.allErrors.mapNotNull {error ->
            when (error) {
                is FieldError -> ValidationErrorResponse(error.field, "hehe",error.defaultMessage ?: "")
                is ObjectError -> ValidationErrorResponse(error.objectName, "hihi", error.defaultMessage ?: "")
                else -> null
            }
        }
        return ResponseEntity.badRequest().body(errorResponse)
    }
}

data class ValidationErrorResponse(
        val fieldName: String,
        val path: String,
        val message: String
)
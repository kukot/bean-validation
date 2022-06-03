package com.kukot.credentialsencryption.linebreak

import network.contour.app.validator.LineLimit
import org.hibernate.validator.constraints.Length
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("linebreak")
class LineBreakController {

    @GetMapping("cr")
    fun getCarriageReturn(): ResponseEntity<String> {
        return ResponseEntity.ok("First line\rSecond line")
    }

    @GetMapping("n")
    fun getLineFeed(): ResponseEntity<String> {
        return ResponseEntity.ok("First line\nSecond line")
    }

    @GetMapping("crlf")
    fun getCrlf(): ResponseEntity<String> {
        return ResponseEntity.ok("First line\r\nSecond line")
    }

    @PostMapping("maxline")
    fun testMaxLine(@Valid @RequestBody documentInput: DocumentInput, bindingResult: BindingResult): ResponseEntity<String> {
        if (bindingResult.allErrors.isNotEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            bindingResult.allErrors.first().let { it.objectName + it.defaultMessage }
                    )
        }
        return ResponseEntity.ok(documentInput.address)
    }
}

class DocumentInput(
        @LineLimit(maxLine = 2, maxCharPerLine = 10)
        val address: String,

        @Valid
        val personalInfo: PersonalInfo,
)

class PersonalInfo(
        @LineLimit(maxLine = 1, maxCharPerLine = 7)
        val name: String
)
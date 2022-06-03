package com.kukot.credentialsencryption.linebreak

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [LineBreakController::class])
internal class LineBreakControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun given_invalid_personal_info_validation_should_fail() {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/linebreak/maxline")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                       "address": "Hoang\nMy Dinh 2",
                                       "personalInfo": {
                                            "name": "Nguyen Nhu Phuc"
                                       }
                                    }
                                """.trimIndent()
                        )
        )
                .andExpect(status().isBadRequest)
    }
}
package com.kukot.credentialsencryption

import com.kukot.credentialsencryption.user.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CredentialsEncryptionApplication {

//    @Component
    class UserListing(
            private val userRepository: UserRepository
    ) : CommandLineRunner {
        override fun run(vararg args: String?) {
            userRepository.findAll()
                    .forEach { println("User: ${it.id}, name: ${it.username}, password: ${it.password}") }
        }

    }
}

fun main(args: Array<String>) {
    val appContext = runApplication<CredentialsEncryptionApplication>(*args)
}

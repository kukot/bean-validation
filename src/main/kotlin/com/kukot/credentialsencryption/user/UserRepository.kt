package com.kukot.credentialsencryption.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users, String> {
}
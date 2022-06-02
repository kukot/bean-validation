package com.kukot.credentialsencryption.user

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Users(

        @Id
        val id: String,

        val username: String,

        val password: String
) {


}
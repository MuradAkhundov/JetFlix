package com.muradakhundov.jetflix.authentication.data.query

sealed class AuthQuery {
    data class RegistrationQuery(val username : String, val email : String, val password : String)
    data class LoginQuery(val email : String, val password: String)
}
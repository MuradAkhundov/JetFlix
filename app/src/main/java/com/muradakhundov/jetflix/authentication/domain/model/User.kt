package com.muradakhundov.jetflix.authentication.domain.model

data class User(
    val id : String,
    val username : String,
    val image : String?,
    val email : String,
)
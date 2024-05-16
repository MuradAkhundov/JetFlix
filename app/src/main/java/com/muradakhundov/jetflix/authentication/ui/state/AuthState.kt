package com.muradakhundov.jetflix.authentication.ui.state

data class AuthState(
    val isLoading : Boolean,
    val error : String? = null,
    val success : Boolean = false
)

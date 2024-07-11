package com.muradakhundov.jetflix.main.ui.state

data class MovieState(
    val isLoading : Boolean,
    val error : String? = null,
    val success : Boolean = false
)

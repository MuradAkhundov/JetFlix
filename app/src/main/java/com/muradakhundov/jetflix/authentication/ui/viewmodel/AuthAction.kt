package com.muradakhundov.jetflix.authentication.ui.viewmodel

import com.muradakhundov.jetflix.authentication.data.query.AuthQuery.RegistrationQuery
import com.muradakhundov.jetflix.authentication.domain.model.User

sealed class AuthAction {
    data class RegisterAction (val user : RegistrationQuery) : AuthAction()
    data class LoginAction (val email : String, val password : String) : AuthAction()
}
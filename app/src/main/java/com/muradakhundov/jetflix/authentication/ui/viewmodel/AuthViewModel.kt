package com.muradakhundov.jetflix.authentication.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradakhundov.jetflix.authentication.data.query.AuthQuery
import com.muradakhundov.jetflix.authentication.data.repository.remote.UserRepository
import com.muradakhundov.jetflix.authentication.ui.state.AuthState
import com.muradakhundov.jetflix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState(isLoading = false))
    val uiState : StateFlow<AuthState> = _uiState.asStateFlow()

    fun setAction(action: AuthAction) {
        viewModelScope.launch {
            _uiState.emit(AuthState(isLoading = true))
            when (action) {
                is AuthAction.LoginAction -> {
                    val answer = repository.login(AuthQuery.LoginQuery(action.email,action.password))
                    when (answer) {
                        is Resource.Success -> {
                            _uiState.emit(AuthState(isLoading = false, success = true))
                        }
                        is Resource.Error -> {
                            _uiState.emit(AuthState(isLoading = false, error = answer.message))
                        }
                    }
                }

                is AuthAction.RegisterAction -> {
                    val answer = repository.register(action.user)
                    when (answer) {
                        is Resource.Success -> {
                            _uiState.emit(AuthState(isLoading = false, success = true))
                        }
                        is Resource.Error -> {
                            _uiState.emit(AuthState(isLoading = false, error = "Something Went Wrong"))
                        }
                    }
                }
            }
        }
    }
}
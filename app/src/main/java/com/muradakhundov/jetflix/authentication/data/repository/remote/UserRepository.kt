package com.muradakhundov.jetflix.authentication.data.repository.remote

import com.muradakhundov.jetflix.authentication.data.query.AuthQuery.RegistrationQuery
import com.muradakhundov.jetflix.authentication.data.source.remote.UserDataSource
import com.muradakhundov.jetflix.util.Resource
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDataSource: UserDataSource) {
    suspend fun register(query : RegistrationQuery) : Resource<Unit> {
        return userDataSource.register(query)
    }
}
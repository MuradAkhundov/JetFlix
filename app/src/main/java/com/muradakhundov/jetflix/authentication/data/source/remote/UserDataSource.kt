package com.muradakhundov.jetflix.authentication.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.muradakhundov.jetflix.authentication.common.Constants
import com.muradakhundov.jetflix.authentication.data.query.AuthQuery
import com.muradakhundov.jetflix.common.data.source.PreferencesDataSource
import com.muradakhundov.jetflix.authentication.domain.model.User
import com.muradakhundov.jetflix.common.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume

class UserDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val actionDataSource : PreferencesDataSource,
    database: FirebaseDatabase
) {
    private val usersRef: DatabaseReference = database.getReference("users")

    suspend fun register(query: AuthQuery.RegistrationQuery): Resource<Unit> {
        return try {
            val authResult =
                firebaseAuth.createUserWithEmailAndPassword(query.email, query.password).await()
            val userId = authResult.user?.uid

            if (userId != null) {
                val user = User(userId, query.username, "", query.email)
                usersRef.child(userId).setValue(user).await()
                actionDataSource.saveBooleanAction(AuthQuery.LocalStorageBooleanQuery(key = Constants.IS_USER_LOGGED_IN,true))
                Resource.Success(Unit)
            } else {
                Resource.Error("User ID is null")
            }
        } catch (e: Exception) {
            Resource.Error("${e.message}")
        }
    }
    suspend fun login(query: AuthQuery.LoginQuery): Resource<Unit> {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(query.email, query.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                actionDataSource.saveBooleanAction(
                                    AuthQuery.LocalStorageBooleanQuery(
                                        key = Constants.IS_USER_LOGGED_IN,
                                        value = true
                                    )
                                )
                                continuation.resume(Resource.Success(Unit))
                            } catch (e: Exception) {
                                continuation.resume(Resource.Error(e.message ?: "Unknown error"))
                            }
                        }
                    } else {
                        val exception = task.exception ?: Exception("Unknown error")
                        continuation.resume(Resource.Error(exception.message ?: "Unknown error"))
                    }
                }
        }
    }
}
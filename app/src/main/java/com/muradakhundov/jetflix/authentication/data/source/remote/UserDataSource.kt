package com.muradakhundov.jetflix.authentication.data.source.remote

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.muradakhundov.jetflix.authentication.data.query.AuthQuery
import com.muradakhundov.jetflix.authentication.domain.model.User
import com.muradakhundov.jetflix.authentication.ui.state.AuthState
import com.muradakhundov.jetflix.util.Resource
import kotlinx.coroutines.tasks.await
import java.util.concurrent.Flow
import javax.inject.Inject


class UserDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    database: FirebaseDatabase
) {
    private val usersRef: DatabaseReference = database.getReference("users")

    suspend fun register(query: AuthQuery.RegistrationQuery): Resource<Unit> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(query.email, query.password).await()
            val userId = authResult.user?.uid

            if (userId != null) {
                val user = User(userId, query.username, query.password, null, query.email)
                usersRef.child(userId).setValue(user).await()
                Resource.Success(Unit)
            } else {
                Resource.Error("User ID is null")
            }
        } catch (e: Exception) {
            Log.e("Murad", "Something Went Wrong", e)
            Resource.Error("Error registering user: ${e.message}")
        }
    }



    fun login(email: String,password: String){

    }

}
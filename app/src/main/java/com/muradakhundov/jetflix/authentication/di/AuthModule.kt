package com.muradakhundov.jetflix.authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.muradakhundov.jetflix.authentication.data.repository.remote.UserRepository
import com.muradakhundov.jetflix.common.data.source.PreferencesDataSource
import com.muradakhundov.jetflix.authentication.data.source.remote.UserDataSource
import com.muradakhundov.jetflix.common.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserActionsDataSource(dataStoreManager: DataStoreManager) : PreferencesDataSource {
        return PreferencesDataSource(dataStoreManager)
    }


    @Provides
    @Singleton
    fun provideUserDataSource(
        firebaseAuth: FirebaseAuth,
        userActionsDataSource: PreferencesDataSource,
        firebaseDatabase: FirebaseDatabase
    ): UserDataSource {
        return UserDataSource(firebaseAuth,userActionsDataSource,firebaseDatabase)
    }


    @Provides
    @Singleton
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository {
        return UserRepository(userDataSource = userDataSource)
    }

}
package com.muradakhundov.jetflix.entry.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.muradakhundov.jetflix.authentication.common.Constants
import com.muradakhundov.jetflix.authentication.data.query.AuthQuery
import com.muradakhundov.jetflix.common.data.source.PreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedEntryViewModel @Inject constructor(private val actionDataSource: PreferencesDataSource) :
    ViewModel() {

    fun setOnboardingSeen() {
        CoroutineScope(Dispatchers.IO).launch {
            actionDataSource.saveBooleanAction(
                AuthQuery.LocalStorageBooleanQuery(
                    key = Constants.IS_ONBOARDING_SEEN,
                    true
                )
            )
        }
    }

    suspend fun getOnboardingSeen(): Boolean {
        return actionDataSource.getBooleanAction(Constants.IS_ONBOARDING_SEEN)
    }

    suspend fun getUserSignedIn(): Boolean {
        return actionDataSource.getBooleanAction(Constants.IS_USER_LOGGED_IN)
    }

}
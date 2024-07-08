package com.muradakhundov.jetflix.common.data.source

import com.muradakhundov.jetflix.authentication.data.query.AuthQuery.LocalStorageBooleanQuery
import com.muradakhundov.jetflix.common.datastore.DataStoreManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(private val dataStoreManager: DataStoreManager) {
    suspend fun saveBooleanAction(query: LocalStorageBooleanQuery){
        dataStoreManager.setBoolean(query.key,query.value)
    }

    suspend fun getBooleanAction(key : String) : Boolean {
        return dataStoreManager.getBoolean(key).first()
    }
}
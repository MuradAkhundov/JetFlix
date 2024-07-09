package com.muradakhundov.jetflix.main.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    var searchText by mutableStateOf("")
        private set

    var isSearching by mutableStateOf(false)
        private set

    fun onSearchTextChange(newText: String) {
        searchText = newText
    }

    fun onToggleSearch() {
        isSearching = !isSearching
    }
}
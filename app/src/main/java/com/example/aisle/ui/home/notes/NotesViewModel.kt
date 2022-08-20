package com.example.aisle.ui.home.notes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisle.data.network.AisleApi
import com.example.aisle.data.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel: ViewModel() {

    private val TAG = "NotesViewModel"
    private var api: AisleApi = RetrofitInstance.aisleApi

    init {

    }

    fun getNotesInfo(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.notes("Bearer ${token}")
            if (response.isSuccessful) {
                val note = response.body()
            } else {
                Log.i(TAG, "An error occurred in NotesViewModel: ${response.errorBody().toString()}")
            }
        }
    }

}
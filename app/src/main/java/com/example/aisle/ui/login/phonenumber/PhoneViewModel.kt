package com.example.aisle.ui.login.phonenumber

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisle.data.network.AisleApi
import com.example.aisle.data.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhoneViewModel : ViewModel() {

    private val TAG = "PhoneViewModel"
    private var api: AisleApi = RetrofitInstance.aisleApi

    private val _isExistingUserLiveData = MutableLiveData<Boolean>()
    val isExistingUserLiveData = _isExistingUserLiveData

    fun invokeIsExistingUserApi(userPhoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.login(userPhoneNumber)
            if (response.isSuccessful) {
                val isExistingUser = response.body()?.status ?: false
                withContext(Dispatchers.Main) {
                    _isExistingUserLiveData.value = isExistingUser
                }
            } else {
                Log.i(
                    TAG,
                    "An error occurred in PhoneViewModel: ${response.errorBody().toString()}"
                )
            }
        }
    }

}
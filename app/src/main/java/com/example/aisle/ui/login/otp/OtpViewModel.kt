package com.example.aisle.ui.login.otp

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisle.data.network.AisleApi
import com.example.aisle.data.network.RetrofitInstance
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class OtpViewModel : ViewModel() {

    private val TAG = "OtpViewModel"
    private var api: AisleApi = RetrofitInstance.aisleApi
    private lateinit var otpCountDownTimer: CountDownTimer
    private lateinit var otpCountDownTimerJob: Job

    private val _otpCountDownTimeLiveData = MutableLiveData<Long>()
    val otpCountDownTimeLiveData = _otpCountDownTimeLiveData

    private val _otpCountDownCompleteStatusLiveData = MutableLiveData<Boolean>()
    val otpCountDownCompleteStatusLiveData = _otpCountDownCompleteStatusLiveData

    private val _otpVerifiedTokenObtainedLiveData = MutableLiveData<String>()
    val otpVerifiedTokenObtainedLiveData = _otpVerifiedTokenObtainedLiveData

    init {
        runBlocking {
            countDownTimer()
        }
    }

    suspend fun countDownTimer() {
        otpCountDownTimerJob = viewModelScope.launch {
            otpCountDownTimer = object : CountDownTimer(59000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    Log.i(TAG, "onTick: ${TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)}")
                    _otpCountDownTimeLiveData.value = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                }

                override fun onFinish() {
                    _otpCountDownCompleteStatusLiveData.value = true
                }
            }.start()
        }
    }

    fun getTokenFromApi(userPhoneNumber: String, otp: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.otp(userPhoneNumber, otp)
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    otpVerifiedTokenObtainedLiveData.value = response.body()?.token
                }
            } else {
                Log.i(
                    TAG,
                    "An error occurred in OtpViewModel: ${response.errorBody().toString()}"
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        otpCountDownTimerJob.cancel()
        otpCountDownTimer.cancel()
    }

}
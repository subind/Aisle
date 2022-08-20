package com.example.aisle.ui.login.otp

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class OtpViewModel : ViewModel() {

    private val TAG = "OtpViewModel"
    private lateinit var otpCountDownTimer: CountDownTimer
    private lateinit var otpCountDownTimerJob: Job

    private val _otpCountDownTimeLiveData = MutableLiveData<Long>()
    val otpCountDownTimeLiveData = _otpCountDownTimeLiveData

    private val _otpCountDownCompleteStatusLiveData = MutableLiveData<Boolean>()
    val otpCountDownCompleteStatusLiveData = _otpCountDownCompleteStatusLiveData

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

    override fun onCleared() {
        super.onCleared()
        otpCountDownTimerJob.cancel()
        otpCountDownTimer.cancel()
    }

}
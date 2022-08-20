package com.example.aisle.data.network

import com.example.aisle.data.network.models.login.Login
import com.example.aisle.data.network.models.note.Note
import com.example.aisle.data.network.models.otp.Otp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AisleApi {

    @POST("users/phone_number_login")
    suspend fun login(
        @Query("number")
        userPhoneNumber: String
    ): Response<Login>


    @POST("users/verify_otp")
    suspend fun otp(
        @Query("number")
        userPhoneNumber: String,
        @Query("otp")
        otp: String
    ): Response<Otp>


    @GET("users/test_profile_list")
    suspend fun notes(
        @Header("Authorization")
        token: String
    ): Response<Note>

}
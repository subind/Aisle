package com.example.aisle.data.network

import com.example.aisle.utils.AppUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val retrofitClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(AppUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(retrofitClient)
                .build()
        }

        val aisleApi by lazy {
            retrofit.create(AisleApi::class.java)
        }

    }

}
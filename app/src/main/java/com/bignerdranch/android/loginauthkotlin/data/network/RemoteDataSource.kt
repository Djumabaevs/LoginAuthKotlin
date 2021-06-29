package com.bignerdranch.android.loginauthkotlin.data.network

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object{
//        private const val BASE_URL = "https://54.36.192.142:8073/api/"
     private const val BASE_URL = "https://simplifiedcoding.tech/mywebapp/public/api/"
    }

    fun<Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor {chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization", "Bearer $authToken")
                        }.build())
                    }.also { client ->
                  if(BuildConfig.DEBUG) {
                      val logging = HttpLoggingInterceptor()
                      logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                      client.addInterceptor(logging)
                  }
                }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}
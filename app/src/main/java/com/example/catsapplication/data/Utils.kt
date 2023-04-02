package com.example.catsapplication.data

import android.util.Log
import coil.network.HttpException
import com.example.catsapplication.domain.Either
import com.example.catsapplication.domain.Failure
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit


fun retrofitInstanceCatApi(): CatApi {

    val okHttpClient = OkHttpClient.Builder()
 //       .addInterceptor(CatInterceptor())
        .build()

    return Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(CatApi::class.java)

}

class CatInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("x-api-key", "live_gBfyLHlx0WyOjeVNOqyiTXCcrXlWWxCc0OkUscYugvNZSIaFkTponA8nxvwJrptf")
            .build()
        return chain.proceed(request)
    }
}

suspend inline fun <T> saveApiCall(crossinline apiCall: suspend () -> T): Either<Failure, T> {
    return try {
        Either.Right(apiCall())
    } catch (e: Exception) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                Either.Left(Failure.ServerError)
            }
            is SocketTimeoutException -> {
                Either.Left(Failure.Timeout)
            }
            is IOException -> {
                Either.Left(Failure.NetworkConnection)
            }
            else -> {
                Either.Left(Failure.Unknown)
            }
        }
    }
}
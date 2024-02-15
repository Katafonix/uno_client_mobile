package com.example.uno.data.accounts

import com.example.uno.data.accounts.entities.GetAccountResponseEntity
import com.example.uno.data.accounts.entities.SignUpResponseEntity
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountsApi {
    @POST("auth/log-in/{id}")
    suspend fun signInById(@Path("id") id: Int)

    @POST("auth/log-out")
    suspend fun logOut()

    @GET("auth")
    suspend fun signIn()

    @POST("users")
    suspend fun signUp(): SignUpResponseEntity

    @GET("auth")
    suspend fun getAccount(): GetAccountResponseEntity
}
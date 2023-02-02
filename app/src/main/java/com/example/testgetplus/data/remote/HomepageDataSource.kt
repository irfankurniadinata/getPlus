package com.example.testgetplus.data.remote

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Home
import com.example.testgetplus.data.model.Layout
import retrofit2.Response
import retrofit2.http.GET

interface HomepageDataSource {
    @GET("/home")
    suspend fun getHomepage(): Response<BaseResponse<Home>>
}
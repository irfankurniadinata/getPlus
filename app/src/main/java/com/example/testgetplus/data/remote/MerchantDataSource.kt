package com.example.testgetplus.data.remote

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Merchant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MerchantDataSource {
    @GET("merchants")
    suspend fun getMerchant(
        @QueryMap queryMap: HashMap<String, Any?>?
    ): Response<BaseResponse<Merchant>>
}
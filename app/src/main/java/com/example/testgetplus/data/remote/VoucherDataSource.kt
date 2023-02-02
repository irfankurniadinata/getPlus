package com.example.testgetplus.data.remote

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Voucher
import retrofit2.Response
import retrofit2.http.GET

interface VoucherDataSource {
    @GET("/vouchers")
    suspend fun getVoucher(): Response<BaseResponse<Voucher>>
}
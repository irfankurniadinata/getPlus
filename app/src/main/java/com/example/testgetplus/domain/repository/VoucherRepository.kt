package com.example.testgetplus.domain.repository

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Voucher
import kotlinx.coroutines.flow.Flow

interface VoucherRepository {
    suspend fun getVoucher(): Flow<BaseResponse<Voucher>>
}
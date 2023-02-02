package com.example.testgetplus.domain.repository

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Voucher
import com.example.testgetplus.data.remote.VoucherDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VoucherRepositoryImpl(private val voucherDataSource: VoucherDataSource) : VoucherRepository {
    override suspend fun getVoucher(): Flow<BaseResponse<Voucher>> {
        return flow {
            val response = voucherDataSource.getVoucher()
            if (response.isSuccessful) {
                val body = response.body()
                emit(body!!)
            }
        }
    }
}
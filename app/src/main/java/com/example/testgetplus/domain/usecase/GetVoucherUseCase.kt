package com.example.testgetplus.domain.usecase

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Voucher
import com.example.testgetplus.domain.repository.VoucherRepository
import kotlinx.coroutines.flow.Flow

class GetVoucherUseCase(private val voucherRepository: VoucherRepository) {
    suspend fun execute(): Flow<BaseResponse<Voucher>> {
        return voucherRepository.getVoucher()
    }
}
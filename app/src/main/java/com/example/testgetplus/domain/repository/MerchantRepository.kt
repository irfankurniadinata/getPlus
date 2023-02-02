package com.example.testgetplus.domain.repository

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Merchant
import kotlinx.coroutines.flow.Flow

interface MerchantRepository {
    suspend fun getMerchant(queryMap: HashMap<String, Any?>?): Flow<BaseResponse<Merchant>>
}
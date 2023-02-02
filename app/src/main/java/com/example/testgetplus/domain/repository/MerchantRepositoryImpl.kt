package com.example.testgetplus.domain.repository

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Merchant
import com.example.testgetplus.data.remote.MerchantDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MerchantRepositoryImpl(private val merchantDataSource: MerchantDataSource) : MerchantRepository {
    override suspend fun getMerchant(queryMap: HashMap<String, Any?>?): Flow<BaseResponse<Merchant>> {
        return flow {
            val response = merchantDataSource.getMerchant(queryMap = queryMap)
            if (response.isSuccessful) {
                val body = response.body()
                emit(body!!)
            }
        }
    }

}
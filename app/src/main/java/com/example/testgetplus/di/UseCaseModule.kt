package com.example.testgetplus.di

import com.example.testgetplus.domain.usecase.GetHomePageUseCase
import com.example.testgetplus.domain.usecase.GetMerchantUseCase
import com.example.testgetplus.domain.usecase.GetVoucherUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetHomePageUseCase(get()) }
    single { GetMerchantUseCase(get()) }
    single { GetVoucherUseCase(get()) }
}
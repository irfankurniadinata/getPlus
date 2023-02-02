package com.example.testgetplus.di

import com.example.testgetplus.presentation.activity.main.MainViewModel
import com.example.testgetplus.presentation.activity.merchant.MerchantViewModel
import com.example.testgetplus.presentation.activity.voucher.VoucherViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { MerchantViewModel(get()) }

    viewModel { VoucherViewModel(get()) }


}
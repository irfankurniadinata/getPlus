package com.example.testgetplus.di

import com.example.testgetplus.data.remote.HomepageDataSource
import com.example.testgetplus.data.remote.MerchantDataSource
import com.example.testgetplus.data.remote.VoucherDataSource
import com.example.testgetplus.domain.repository.*
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { provideHomepageService(get()) }
    single { provideHomepageRepository(get()) }

    single { provideMerchantService(get()) }
    single { provideMerchantRepository(get()) }

    single { provideVoucherService(get()) }
    single { provideVoucherRepository(get()) }
}

fun provideHomepageService(retrofit: Retrofit) : HomepageDataSource {
    return retrofit.create(HomepageDataSource::class.java)
}

fun provideHomepageRepository(service: HomepageDataSource) : HomepageRepository {
    return HomepageRepositoryImpl(service)
}

fun provideMerchantService(retrofit: Retrofit) : MerchantDataSource {
    return retrofit.create(MerchantDataSource::class.java)
}

fun provideMerchantRepository(service: MerchantDataSource) : MerchantRepository {
    return MerchantRepositoryImpl(service)
}

fun provideVoucherService(retrofit: Retrofit) : VoucherDataSource {
    return retrofit.create(VoucherDataSource::class.java)
}

fun provideVoucherRepository(service: VoucherDataSource) : VoucherRepository {
    return VoucherRepositoryImpl(service)
}
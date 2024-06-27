package com.ffcs.primeiramarcha.di

import com.ffcs.primeiramarcha.constants.Constants
import com.ffcs.primeiramarcha.interfaces.PrimeiraMarchaService
import com.ffcs.primeiramarcha.repository.OficinasRepository
import com.ffcs.primeiramarcha.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.HINOVA_MOBILE_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    single<PrimeiraMarchaService> { get<Retrofit>().create(PrimeiraMarchaService::class.java) }
}

val repositoryModule = module {
    single<OficinasRepository> { OficinasRepository(get()) }
}

val viewModelModule = module {
    viewModel<MainViewModel> { MainViewModel(get()) }
}

val appModules = listOf(
    retrofitModule, repositoryModule, viewModelModule
)
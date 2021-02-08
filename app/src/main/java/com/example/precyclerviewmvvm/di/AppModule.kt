package com.example.precyclerviewmvvm.di

import android.content.Context
import com.example.pprecyclerviewmvvm.db.AppDatabase
import com.example.pprecyclerviewmvvm.db.UserPreferences
import com.example.precyclerviewmvvm.network.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoviesApi() : MoviesApi = Retrofit.Builder()
        .baseUrl("https://api.simplifiedcoding.in/course-apis/recyclerview/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getRetrofitClient())
        .build()
        .create(MoviesApi::class.java)

    private fun getRetrofitClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase = AppDatabase(appContext)


    @Singleton
    @Provides
    fun provideDataPreferences(@ApplicationContext appContext: Context): UserPreferences = UserPreferences(appContext)



}
package com.kodesparkle.sportdbfdj.di

import com.kodesparkle.sportdbfdj.data.remote.team.repository.RemoteTeamRepositoryImpl
import com.kodesparkle.sportdbfdj.data.remote.team.service.TeamService
import com.kodesparkle.sportdbfdj.domain.repository.RemoteTeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://www.thesportsdb.com/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): TeamService =
        retrofit.create(TeamService::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: TeamService): RemoteTeamRepository =
        RemoteTeamRepositoryImpl(apiService)


}

package com.kodesparkle.sportdbfdj.di

import android.content.Context
import com.kodesparkle.sportdbfdj.data.remote.league.repository.RemoteLeagueRepositoryImpl
import com.kodesparkle.sportdbfdj.data.remote.league.service.LeagueService
import com.kodesparkle.sportdbfdj.data.remote.team.repository.RemoteTeamRepositoryImpl
import com.kodesparkle.sportdbfdj.data.remote.team.service.TeamService
import com.kodesparkle.sportdbfdj.domain.repository.RemoteLeagueRepository
import com.kodesparkle.sportdbfdj.domain.repository.RemoteTeamRepository
import com.kodesparkle.sportdbfdj.utils.CacheInterceptor
import com.kodesparkle.sportdbfdj.utils.NetworkUtils
import com.kodesparkle.sportdbfdj.utils.NetworkUtils.getCacheSize
import com.kodesparkle.sportdbfdj.utils.NetworkUtils.hasNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://www.thesportsdb.com/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(@ApplicationContext  appContext: Context, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .cache(getCacheSize(appContext))
            .addNetworkInterceptor(CacheInterceptor())
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
    fun provideTeamService(retrofit: Retrofit): TeamService =
        retrofit.create(TeamService::class.java)

    @Singleton
    @Provides
    fun provideLeagueService(retrofit: Retrofit): LeagueService =
        retrofit.create(LeagueService::class.java)

    @Singleton
    @Provides
    fun providesTeamRepository(apiService: TeamService): RemoteTeamRepository =
        RemoteTeamRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun providesLeagueRepository(apiService: LeagueService): RemoteLeagueRepository =
        RemoteLeagueRepositoryImpl(apiService)


}

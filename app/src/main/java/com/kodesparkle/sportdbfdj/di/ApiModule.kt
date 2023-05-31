package com.kodesparkle.sportdbfdj.di

import android.content.Context
import com.kodesparkle.sportdbfdj.data.remote.league.repository.RemoteLeagueRepositoryImpl
import com.kodesparkle.sportdbfdj.data.remote.league.service.LeagueService
import com.kodesparkle.sportdbfdj.data.remote.team.repository.RemoteTeamRepositoryImpl
import com.kodesparkle.sportdbfdj.data.remote.team.service.TeamService
import com.kodesparkle.sportdbfdj.domain.repository.RemoteLeagueRepository
import com.kodesparkle.sportdbfdj.domain.repository.RemoteTeamRepository
import com.kodesparkle.sportdbfdj.utils.NetworkUtils
import com.kodesparkle.sportdbfdj.utils.NetworkUtils.getCacheSize
import com.kodesparkle.sportdbfdj.utils.NetworkUtils.hasNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesOkHttpClient(@ApplicationContext  appContext: Context, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .cache(getCacheSize(appContext))
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->

                // Get the request from the chain.
                var request = chain.request()

                /*
                *  Leveraging the advantage of using Kotlin,
                *  we initialize the request and change its header depending on whether
                *  the device is connected to Internet or not.
                */
                request = if (hasNetwork(appContext)!!)
                /*
                *  If there is Internet, get the cache that was stored 5 seconds ago.
                *  If the cache is older than 5 seconds, then discard it,
                *  and indicate an error in fetching the response.
                *  The 'max-age' attribute is responsible for this behavior.
                */
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                /*
                *  If there is no Internet, get the cache that was stored 7 days ago.
                *  If the cache is older than 7 days, then discard it,
                *  and indicate an error in fetching the response.
                *  The 'max-stale' attribute is responsible for this behavior.
                *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                */
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                // End of if-else statement

                // Add the modified request to the chain.
                chain.proceed(request)
            }
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

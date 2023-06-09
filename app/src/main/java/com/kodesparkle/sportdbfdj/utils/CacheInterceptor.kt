package com.kodesparkle.sportdbfdj.utils

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    val CACHE_CONTROL_HEADER = "Cache-Control"
    val CACHE_CONTROL_NO_CACHE = "no-cache"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalResponse = chain.proceed(request)

        val shouldUseCache = request.header(CACHE_CONTROL_HEADER) != CACHE_CONTROL_NO_CACHE
        if (!shouldUseCache) return originalResponse

        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.MINUTES)
            .build()

        return originalResponse.newBuilder()
            .header(CACHE_CONTROL_HEADER, cacheControl.toString())
            .build()
    }
}

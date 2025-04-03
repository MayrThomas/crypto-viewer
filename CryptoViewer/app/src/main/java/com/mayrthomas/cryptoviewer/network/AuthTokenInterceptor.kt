package com.mayrthomas.cryptoviewer.network

import com.mayrthomas.cryptoviewer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
            .header("x-cg-demo-api-key", BuildConfig.API_KEY)

        return chain.proceed(builder.build())
    }
}
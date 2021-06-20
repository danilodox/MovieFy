package com.br.moviefy.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    // Vou deixar a URL e a API KEY exposta apenas para facilitar para o time de avaliação.
    // Depois irei colcoar no gradle

    companion object {
        const val API_URL = "f321a808e68611f41312aa8408531476"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url.newBuilder().addQueryParameter("api_key", API_URL).addQueryParameter("language", "pt-PT").build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
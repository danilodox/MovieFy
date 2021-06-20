package com.br.moviefy.data.network


import com.br.moviefy.data.network.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitService {

    // Vou deixar a URL e a API KEY exposta apenas para facilitar para o time de avaliação.
    // Depois irei colcoar no gradle
    companion object {

        const val BASE_URL = "https://api.themoviedb.org/3/"

         val interceptor = AuthInterceptor()
    }




    private fun provideHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .apply {
                        val logging = HttpLoggingInterceptor()
                        logging.level = HttpLoggingInterceptor.Level.BODY
                        addInterceptor(interceptor)
                        addInterceptor(logging)
                    }.build()

    var service: ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient())
            .build()
            .create(ApiService::class.java)



}
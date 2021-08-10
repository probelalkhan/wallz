package net.simplifiedcoding.data.network

import androidx.viewbinding.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    companion object {
        private const val ACCESS_KEY = "YOUR_ACCESS_KEY_HERE_1"
        private const val BASE_URL = "https://api.unsplash.com/"
    }

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("client_id", ACCESS_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
package ru.multimodule.network_impl.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.multimodule.network_api.RickAndMortyApi
import ru.multimodule.network_impl.BuildConfig
import ru.multimodule.network_impl.NetworkAdapterFactory
import javax.inject.Singleton

@Module
interface NetworkModule {

    companion object{

        @Singleton
        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor{
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply {
                level = if (BuildConfig.DEBUG){
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
            return interceptor
        }

        @Singleton
        @Provides
        fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
            return OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofitApi(okhttpClient: OkHttpClient): RickAndMortyApi {
            val contentType = "application/json".toMediaType()
            val json = Json {
                ignoreUnknownKeys = true
            }

            return Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(json.asConverterFactory(contentType))
                .addCallAdapterFactory(NetworkAdapterFactory())
                .client(okhttpClient)
                .build()
                .create(RickAndMortyApi::class.java)
        }
    }
}
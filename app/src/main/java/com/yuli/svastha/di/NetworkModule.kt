package com.yuli.svastha.di

import retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.yuli.svastha.data.net.SvasthaApi
import dagger.Module; import dagger.Provides; import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module @InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides @Singleton fun json(): Json = Json { ignoreUnknownKeys = true; isLenient = true }
  @Provides @Singleton fun okHttp(): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }).build()
  @Provides @Singleton fun retrofit(json: Json, client: OkHttpClient): Retrofit =
    Retrofit.Builder()
      .baseUrl("http://10.0.2.2:8080")
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .client(client).build()
  @Provides @Singleton fun api(retrofit: Retrofit): SvasthaApi = retrofit.create(SvasthaApi::class.java)
}

package com.edumate.greenify.core.network.di

import com.edumate.greenify.core.network.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClientEngin(): HttpClientEngine {
        return CIO.create()
    }

    @Provides
    fun provideHttpClient(engine: HttpClientEngine): HttpClient {
        return HttpClientFactory.create(engine)
    }
}
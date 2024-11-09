package com.edumate.greenify.core.network.utils

import com.edumate.greenify.core.common.NetworkError
import com.edumate.greenify.core.common.Result
import com.edumate.greenify.core.network.BuildConfig
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: (token: String) -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        val token = BuildConfig.TREFLE_TOKEN
        execute(token)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}
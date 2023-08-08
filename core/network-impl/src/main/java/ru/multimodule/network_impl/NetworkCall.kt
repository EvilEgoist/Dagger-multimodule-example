package ru.multimodule.network_impl

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import ru.multimodule.network_api.ServerResponse
import java.io.IOException

internal class NetworkCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<ServerResponse<S, E>> {

    override fun enqueue(callback: Callback<ServerResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkCall,
                            Response.success(ServerResponse.Success(body))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkCall,
                            Response.success(
                                ServerResponse.UnknownError(
                                    null
                                )
                            )
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }

                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkCall,
                            Response.success(
                                ServerResponse.ApiError(
                                    errorBody, code
                                )
                            )
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkCall,
                            Response.success(
                                ServerResponse.UnknownError(
                                    null
                                )
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> ServerResponse.NetworkConnectionError(throwable)
                    else -> ServerResponse.UnknownError(throwable)
                }
                callback.onResponse(
                    this@NetworkCall,
                    Response.success((networkResponse))
                )
            }
        })
    }

    override fun clone(): Call<ServerResponse<S, E>> {
        return NetworkCall(delegate.clone(), errorConverter)
    }

    override fun execute(): Response<ServerResponse<S, E>> {
        throw java.lang.UnsupportedOperationException("NetworkCall doesn't support execute")
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun cancel() {
        return delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }
}
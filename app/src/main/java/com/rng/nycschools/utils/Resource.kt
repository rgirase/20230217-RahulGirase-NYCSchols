package com.rng.nycschools.utils

sealed class Resource<T>(val data: T? = null, message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(val message: String?, data: T?) : Resource<T>(data, message)

    class Loading<T>(val isLoading: Boolean) : Resource<T>(null)
}

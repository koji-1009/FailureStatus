package com.app.dr1009.failurestatus

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class FailureStatus(
    val message: String? = "",
    val throwable: Throwable? = null
) {
    companion object {
        fun create(message: String?) = FailureStatus(message = message ?: "", throwable = null)
        fun create(t: Throwable?) = FailureStatus(message = "", throwable = t)
    }
}

data class NetworkState(
    val status: Status,
    val failure: FailureStatus? = null
) {
    companion object {
        val LOADED = NetworkState(status = Status.SUCCESS)
        val LOADING = NetworkState(status = Status.LOADING)
        fun error(message: String?) = NetworkState(status = Status.ERROR, failure = FailureStatus.create(message))
        fun error(t: Throwable) = NetworkState(status = Status.ERROR, failure = FailureStatus.create(t))
    }
}

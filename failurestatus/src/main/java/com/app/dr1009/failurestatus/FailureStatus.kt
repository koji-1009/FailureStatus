package com.app.dr1009.failurestatus

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class FailureStatus(val message: String? = null, val throwable: Throwable? = null) {
    constructor(message: String?) : this(message = message, throwable = null)
    constructor(t: Throwable?) : this(message = null, throwable = t)

    fun createMessage(throwableFunc: (throwable: Throwable?) -> String): String =
        if (!message.isNullOrEmpty()) {
            message
        } else {
            throwableFunc(throwable)
        }
}

data class NetworkState(
    val status: Status,
    val failure: FailureStatus? = null
) {
    companion object {
        val LOADED = NetworkState(status = Status.SUCCESS)
        val LOADING = NetworkState(status = Status.LOADING)
        fun error(message: String?) = NetworkState(status = Status.ERROR, failure = FailureStatus(message))
        fun error(t: Throwable) = NetworkState(status = Status.ERROR, failure = FailureStatus(t))
    }
}

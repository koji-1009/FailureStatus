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

sealed class NetworkState(val status: Status) {
    object LOADED: NetworkState(Status.SUCCESS)
    object LOADING: NetworkState(Status.LOADING)
    data class ERROR(val error: FailureStatus): NetworkState(Status.ERROR) {
        constructor(throwable: Throwable?) : this(FailureStatus(throwable))
        constructor(message: String?) : this(FailureStatus(message))
    }
}

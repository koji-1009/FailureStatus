package com.app.dr1009.failurestatus

/**
 * Result status.
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

/**
 * FailureStatus is a class that encapsulates a Throwable.
 *
 * @param message Error message for User.
 * @param throwable Cause of the error.
 */
data class FailureStatus(val message: String? = null, val throwable: Throwable? = null) {
    constructor(message: String?) : this(message = message, throwable = null)
    constructor(t: Throwable?) : this(message = null, throwable = t)

    /**
     * Get an error message from the cause. If the message has already been set, the function is aborted.
     */
    fun createMessage(throwableFunc: (throwable: Throwable?) -> String): String =
        if (!message.isNullOrEmpty()) {
            message
        } else {
            throwableFunc(throwable)
        }
}

/**
 * The state of network access.
 */
sealed class NetworkState(val status: Status) {
    object LOADED : NetworkState(Status.SUCCESS)
    object LOADING : NetworkState(Status.LOADING)
    data class ERROR(val error: FailureStatus) : NetworkState(Status.ERROR) {
        constructor(throwable: Throwable?) : this(FailureStatus(throwable))
        constructor(message: String?) : this(FailureStatus(message))
    }
}

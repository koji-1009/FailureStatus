package com.app.dr1009.failurestatus

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View

fun Activity.networkErroBar(
    anchor: View,
    status: FailureStatus,
    message: (throwable: Throwable?) -> String,
    duration: Int = Snackbar.LENGTH_SHORT,
    isShowAction: Boolean = true,
    actionMessage: (throwable: Throwable?) -> String = { getString(android.R.string.ok) },
    action: (throwable: Throwable?) -> Unit = {}
) {
    val bar = Snackbar.make(anchor, status.createMessage(message), duration)
    if (isShowAction) {
        bar.setAction(actionMessage(status.throwable)) { action(status.throwable) }
    }

    bar.show()
}

fun Fragment.networkErrorBar(
    anchor: View,
    status: FailureStatus,
    message: (throwable: Throwable?) -> String,
    duration: Int = Snackbar.LENGTH_SHORT,
    isShowAction: Boolean = true,
    actionMessage: (throwable: Throwable?) -> String = { getString(android.R.string.ok) },
    action: (throwable: Throwable?) -> Unit = {}
) {
    val bar = Snackbar.make(anchor, status.createMessage(message), duration)
    if (isShowAction) {
        bar.setAction(actionMessage(status.throwable)) { action(status.throwable) }
    }

    bar.show()
}
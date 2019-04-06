package com.app.dr1009.failurestatus

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View

fun Activity.networkErroBar(
    anchor: View,
    status: FailureStatus,
    throwableFunc: (throwable: Throwable?) -> String,
    duration: Int = Snackbar.LENGTH_SHORT,
    isShowAction: Boolean = true,
    actionMessage: String = getString(android.R.string.ok),
    action: () -> Unit = {}
) {
    val message = status.createMessage(throwableFunc)
    val bar = Snackbar.make(anchor, message, duration)
    if (isShowAction) {
        bar.setAction(actionMessage) { action() }
    }

    bar.show()
}

fun Fragment.networkErrorBar(
    anchor: View,
    status: FailureStatus,
    throwableFunc: (throwable: Throwable?) -> String,
    duration: Int = Snackbar.LENGTH_SHORT,
    isShowAction: Boolean = true,
    actionMessage: String = getString(android.R.string.ok),
    action: () -> Unit = {}
) {
    val message = status.createMessage(throwableFunc)
    val bar = Snackbar.make(anchor, message, duration)
    if (isShowAction) {
        bar.setAction(actionMessage) { action() }
    }

    bar.show()
}
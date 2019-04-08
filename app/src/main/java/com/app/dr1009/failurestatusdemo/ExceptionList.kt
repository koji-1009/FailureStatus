package com.app.dr1009.failurestatusdemo

import java.io.IOException

enum class ExceptionList(val throwable: Throwable) {
    RUNTIME(RuntimeException()),
    IO(IOException()),
    OTHER(Exception())
}
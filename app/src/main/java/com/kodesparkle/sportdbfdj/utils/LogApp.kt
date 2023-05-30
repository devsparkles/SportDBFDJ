package com.kodesparkle.sportdbfdj.utils

import timber.log.Timber

object LogApp {

    fun d(text: String?) {
        Timber.d(text)
    }

    fun e(text: String?) {
        Timber.e(text)
    }
}

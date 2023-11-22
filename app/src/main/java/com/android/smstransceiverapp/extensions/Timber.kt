package com.android.smstransceiverapp.extensions

import timber.log.Timber

private const val TAG = "timber"

fun logd(message: String, tag: String = TAG) = Timber.tag(tag).d(message)

fun logd(exception: Throwable, tag: String = TAG) = Timber.tag(tag).d(exception)

fun logd(any: Any?, tag: String = TAG) = Timber.tag(tag).d(any.toString())
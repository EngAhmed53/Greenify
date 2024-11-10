package com.edumate.greenify.utils

import com.edumate.greenify.R
import com.edumate.greenify.core.common.NetworkError

fun NetworkError.toStringResource(): Int {
    return when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NOT_FOUND -> R.string.error_reached_last_page
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.SERVER_ERROR, NetworkError.UNKNOWN -> R.string.error_unknown
    }
}
package com.example.timeledger.domain.model

data class FocusSession(
    val taskName: String,
    val startTime: Long,
    val endTime: Long
) {
    val duration: Long
        get() = endTime - startTime
}

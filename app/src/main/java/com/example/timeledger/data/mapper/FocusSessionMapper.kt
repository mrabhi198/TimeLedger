package com.example.timeledger.data.mapper

import com.example.timeledger.data.local.entity.FocusSessionEntity
import com.example.timeledger.domain.model.FocusSession

fun FocusSessionEntity.toDomain(): FocusSession {
    return FocusSession(
        taskName = taskName,
        startTime = startTime,
        endTime = endTime
    )
}

fun FocusSession.toEntity(): FocusSessionEntity {
    return FocusSessionEntity(
        taskName = taskName,
        startTime = startTime,
        endTime = endTime
    )
}

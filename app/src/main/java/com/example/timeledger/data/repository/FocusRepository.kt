package com.example.timeledger.data.repository

import com.example.timeledger.data.local.dao.FocusSessionDao
import com.example.timeledger.data.mapper.toDomain
import com.example.timeledger.data.mapper.toEntity
import com.example.timeledger.domain.model.FocusSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FocusRepository(
    private val dao: FocusSessionDao
) {

    suspend fun addSession(session: FocusSession) {
        dao.insertSession(session.toEntity())
    }

    fun getSessions(): Flow<List<FocusSession>> {
        return dao.getSessions().map { list ->
            list.map { it.toDomain() }
        }
    }

    fun getTotalFocusTime(): Flow<Long> {
        return dao.getTotalFocusTime().map { it ?: 0L }
    }
}

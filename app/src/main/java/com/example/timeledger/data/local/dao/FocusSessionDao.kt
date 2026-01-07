package com.example.timeledger.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.timeledger.data.local.entity.FocusSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FocusSessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: FocusSessionEntity)

    @Query("SELECT * FROM focus_sessions ORDER BY startTime DESC")
    fun getSessions(): Flow<List<FocusSessionEntity>>

    @Query("SELECT SUM(endTime - startTime) FROM focus_sessions")
    fun getTotalFocusTime(): Flow<Long?>
}

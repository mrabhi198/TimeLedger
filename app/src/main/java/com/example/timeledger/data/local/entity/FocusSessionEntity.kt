package com.example.timeledger.data.local.entity

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.example.timeledger.data.local.dao.FocusSessionDao
import com.example.timeledger.data.local.entity.FocusSessionEntity

@Database(
    entities = [FocusSessionEntity::class],
    version = 1
)
abstract class FocusDatabase : RoomDatabase() {
    abstract fun focusSessionDao(): FocusSessionDao
}

@Entity(tableName = "focus_sessions")
data class FocusSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val taskName: String,
    val startTime: Long,
    val endTime: Long
)
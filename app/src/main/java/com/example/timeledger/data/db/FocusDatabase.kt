package com.example.timeledger.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.timeledger.data.local.dao.FocusSessionDao
import com.example.timeledger.data.local.entity.FocusSessionEntity


@Database(
    entities = [FocusSessionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FocusDatabase : RoomDatabase() {
    abstract fun focusSessionDao(): FocusSessionDao
}

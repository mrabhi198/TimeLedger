package com.example.timeledger

import android.app.Application
import com.example.timeledger.data.local.FocusDatabase
import com.example.timeledger.data.repository.FocusRepository

class TimeLedgerApp : Application() {

    lateinit var repository: FocusRepository
        private set

    override fun onCreate() {
        super.onCreate()

        val db = FocusDatabase.getDatabase(this)
        repository = FocusRepository(db.focusSessionDao())
    }
}

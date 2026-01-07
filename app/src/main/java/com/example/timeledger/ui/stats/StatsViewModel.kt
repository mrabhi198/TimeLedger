package com.example.timeledger.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timeledger.data.repository.FocusRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class StatsViewModel(
    repository: FocusRepository
) : ViewModel() {

    val sessions = repository.getSessions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val totalFocusTime = repository.getTotalFocusTime()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0L)
}

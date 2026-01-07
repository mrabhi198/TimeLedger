package com.example.timeledger.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timeledger.data.repository.FocusRepository

class StatsViewModelFactory(
    private val repository: FocusRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatsViewModel(repository) as T
    }
}

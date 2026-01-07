package com.example.timeledger.ui.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timeledger.data.repository.FocusRepository

class TimerViewModelFactory(
    private val repository: FocusRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TimerViewModel(repository) as T
    }
}

package com.example.timeledger.ui.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timeledger.data.repository.FocusRepository
import com.example.timeledger.domain.model.FocusSession
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimerViewModel(
    private val repository: FocusRepository
) : ViewModel() {

    private val _elapsedTime = MutableStateFlow(0L)
    val elapsedTime = _elapsedTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning = _isRunning.asStateFlow()

    private var startTime = 0L

    fun startTimer() {
        if (_isRunning.value) return

        _isRunning.value = true
        startTime = System.currentTimeMillis()

        viewModelScope.launch {
            while (_isRunning.value) {
                delay(1000)
                _elapsedTime.value += 1000
            }
        }
    }

    fun pauseTimer() {
        _isRunning.value = false
    }

    fun stopTimer(taskName: String) {
        _isRunning.value = false

        if (taskName.isNotBlank() && startTime > 0L) {
            val session = FocusSession(
                taskName = taskName,
                startTime = startTime,
                endTime = System.currentTimeMillis()
            )

            viewModelScope.launch {
                repository.addSession(session)
            }
        }

        _elapsedTime.value = 0L
        startTime = 0L
    }
}

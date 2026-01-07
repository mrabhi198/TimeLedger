package com.example.timeledger.ui.timer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.timeledger.TimeLedgerApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen() {
    val context = LocalContext.current
    val repository =
        (context.applicationContext as TimeLedgerApp).repository

    val viewModel: TimerViewModel = viewModel(
        factory = TimerViewModelFactory(repository)
    )

    var taskName by remember { mutableStateOf("") }

    val elapsedTime by viewModel.elapsedTime.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task name") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = formatTime(elapsedTime),
            style = MaterialTheme.typography.displayMedium
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { viewModel.startTimer() }) {
                Text("Start")
            }
            Button(onClick = { viewModel.pauseTimer() }) {
                Text("Pause")
            }
            Button(onClick = { viewModel.stopTimer(taskName) }) {
                Text("Stop")
            }
        }
    }
}

private fun formatTime(timeMillis: Long): String {
    val totalSeconds = timeMillis / 1000
    val hours = totalSeconds / 3600
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d:%02d".format(hours, minutes, seconds)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TimerScreenPreview() {
    MaterialTheme {
        TimerScreen()
    }
}

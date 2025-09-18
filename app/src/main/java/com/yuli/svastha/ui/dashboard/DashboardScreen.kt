// app/src/main/java/com/yuli/svastha/ui/dashboard/DashboardScreen.kt
package com.yuli.svastha.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
  state: DashboardState,
  onRefresh: () -> Unit
) {
  Scaffold(
    topBar = { TopAppBar(title = { Text("Svastha") }) },
    floatingActionButton = {
      FloatingActionButton(onClick = onRefresh) { Text("↻") }
    }
  ) { pad ->
    Box(
      Modifier
        .padding(pad)
        .fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      when {
        state.loading -> CircularProgressIndicator()
        state.error != null -> Text("Error: ${state.error}")
        else -> Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          Text(
            "Heart rate (avg 24h): ${state.hrAvg ?: "--"} bpm",
            style = MaterialTheme.typography.titleLarge
          )
          Text(
            "Respiratory rate (avg 24h): ${state.rrAvg ?: "--"} bpm",
            style = MaterialTheme.typography.titleLarge
          )
          Text(
            "This is the starter UI. Add charts & thresholds next.",
            style = MaterialTheme.typography.bodyMedium
          )
        }
      }
    }
  }
}

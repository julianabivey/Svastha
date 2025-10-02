// app/src/main/java/com/yuli/svastha/ui/dashboard/DashboardScreen.kt
package com.yuli.svastha.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*

import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModel
import com.patrykandpatrick.vico.core.cartesian.data.ColumnCartesianLayerModel

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

  val chart = rememberCartesianChart(
    rememberColumnCartesianLayer(),
    startAxis = VerticalAxis.rememberStart(),
    bottomAxis = HorizontalAxis.rememberBottom(),
  )

  val model = remember {
    CartesianChartModel(
      ColumnCartesianLayerModel.build {
        // sample data; replace with your real points
        series(1, 2, 4, 8, 3, 10, 4, 7, 2, 6, 4, 8)
      }
    )
  }

  CartesianChartHost(
    chart = chart,
    model = model,
    modifier = Modifier
      .fillMaxWidth()
      .height(180.dp)
      .padding(16.dp)
  )
}

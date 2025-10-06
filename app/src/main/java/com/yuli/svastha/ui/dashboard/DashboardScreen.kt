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
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.component.shapeComponent
import com.patrykandpatrick.vico.compose.common.insets
import com.patrykandpatrick.vico.compose.common.shape.rounded
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModel
import com.patrykandpatrick.vico.core.cartesian.data.LineCartesianLayerModel
import com.patrykandpatrick.vico.core.cartesian.decoration.HorizontalLine
import com.patrykandpatrick.vico.core.common.Position
import com.patrykandpatrick.vico.core.common.component.LineComponent
import com.patrykandpatrick.vico.core.common.shape.CorneredShape
import com.yuli.svastha.ui.utils.ThresholdUi

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

          if (state.heartRateSeries.isNotEmpty()) {

            val hrMin = state.hrThreshold.min
            val hrMax = state.hrThreshold.max

            val chart = rememberCartesianChart(
              rememberLineCartesianLayer(),
              startAxis = VerticalAxis.rememberStart(),
              bottomAxis = HorizontalAxis.rememberBottom(),
              decorations = listOf(
                rememberHorizontalLine(hrMin.toDouble(), "Heart Rate Min"),
                rememberHorizontalLine(hrMax.toDouble(), "Heart Rate Max")
              )
            )

            val model = remember(state.heartRateSeries) {
              CartesianChartModel(
                LineCartesianLayerModel.build {
                  series(state.heartRateSeries)
                }
              )
            }

            CartesianChartHost(
              chart = chart,
              model = model,
              modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(24.dp)
            )
          } else {
            Text(
              "No chart data available.",
              style = MaterialTheme.typography.bodyMedium
            )
          }

          if (state.respRateSeries.isNotEmpty()) {

            val rrMin = state.rrThreshold.min
            val rrMax = state.rrThreshold.max

            val chart = rememberCartesianChart(
              rememberLineCartesianLayer(),
              startAxis = VerticalAxis.rememberStart(),
              bottomAxis = HorizontalAxis.rememberBottom(),
              decorations = listOf(
                rememberHorizontalLine(rrMin.toDouble(), "Respiratory Rate Min"),
                rememberHorizontalLine(rrMax.toDouble(), "Respiratory Rate Max")
              )
            )

            val model = remember(state.respRateSeries) {
              CartesianChartModel(
                LineCartesianLayerModel.build {
                  series(state.respRateSeries)
                }
              )
            }

            CartesianChartHost(
              chart = chart,
              model = model,
              modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(24.dp)
            )
          } else {
            Text(
              "No chart data available.",
              style = MaterialTheme.typography.bodyMedium
            )

            }
          }
        }
      }
    }
  }

@Composable
private fun rememberHorizontalLine(boundary: Double, label: CharSequence): HorizontalLine {
  val boxFill = ThresholdUi.BOX_FILL
  val lineFill = ThresholdUi.LINE_FILL
  val lineThickness = ThresholdUi.LINE_THICKNESS
  val line = rememberLineComponent(fill = lineFill, thickness = lineThickness)
  val labelComponent =
    rememberTextComponent(
      margins = insets(start = 6.dp),
      padding = insets(start = 8.dp, end = 8.dp, bottom = 2.dp),
      background =
        shapeComponent(boxFill, CorneredShape.rounded(bottomLeft = 4.dp, bottomRight = 4.dp)),
    )
  return remember {
    HorizontalLine(
      y = { boundary },
      line = line,
      labelComponent = labelComponent,
      label = { label },
      verticalLabelPosition = Position.Vertical.Bottom,
    )
  }
}


package com.yuli.svastha.ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.core.common.Fill
import com.patrykandpatrick.vico.core.common.Insets
import com.patrykandpatrick.vico.core.common.shape.Shape
import com.yuli.svastha.domain.Zone

//UI Constants
object ThresholdUi {
    val LINE_FILL = Fill.Black
    val BOX_FILL = Fill.Transparent
    val LINE_THICKNESS = 2.dp
    val SHAPE = Shape.Rectangle
    val MARGINS = Insets.Zero
    //val STROKE_FILL = SolidColor(Color(LINE_FILL))
    const val STROKE_THICKNESS_DP = 0f
    val SHADOW = null
}

object Zones {
    val Low = Color(0xFF1E88E5)    // blue-ish
    val Normal = Color(0xFF43A047) // green
    val High = Color(0xFFE53935)   // red
}

fun zoneColor(zone: Zone) = when (zone) {
    Zone.Low -> Zones.Low
    Zone.Normal -> Zones.Normal
    Zone.High -> Zones.High
}

fun zoneLabel(zone: Zone) = when (zone) {
    Zone.Low -> "Low"
    Zone.Normal -> "Normal"
    Zone.High -> "High"
}
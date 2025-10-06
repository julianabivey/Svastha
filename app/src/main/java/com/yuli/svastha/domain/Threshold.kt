package com.yuli.svastha.domain

data class Threshold(val min: Float, val max: Float) {
    init { require(min < max) { "Threshold min must be < max." } }
    fun zone(value: Float): Zone = when {
        value < min -> Zone.Low
        value > max -> Zone.High
        else -> Zone.Normal
    }
}

enum class Zone { Low, Normal, High }

object DefaultThresholds {
    val HR = Threshold(min = 60f, max = 100f)    // bpm
    val RR = Threshold(min = 12f, max = 20f)     // breaths/min
}

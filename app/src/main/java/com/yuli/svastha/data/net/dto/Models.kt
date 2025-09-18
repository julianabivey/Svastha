package com.yuli.svastha.data.net.dto
import kotlinx.serialization.Serializable

@Serializable data class SampleDto(val ts: String, val value: Double)
@Serializable data class SeriesDto(val metric: String, val samples: List<SampleDto>)
@Serializable data class SummaryDto(
  val date: String,
  val heartRate: Map<String, Double>,
  val respiratoryRate: Map<String, Double>
)

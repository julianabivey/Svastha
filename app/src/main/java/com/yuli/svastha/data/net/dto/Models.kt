package com.yuli.svastha.data.net.dto
import kotlinx.serialization.Serializable

@Serializable data class SampleDto(val ts: String, val value: Float)
@Serializable data class SeriesDto(val metric: String, val samples: List<SampleDto>)
@Serializable data class SummaryDto(
  val date: String,
  val heartRate: Map<String, Float>,
  val respiratoryRate: Map<String, Float>
)

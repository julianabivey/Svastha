package com.yuli.svastha.data.repo
import com.yuli.svastha.data.db.SampleEntity
import com.yuli.svastha.data.db.SummaryEntity

interface BiometricsRepository {
  suspend fun refresh(): Result<Unit>
  suspend fun getHeartSeries(): List<SampleEntity>
  suspend fun getRespSeries(): List<SampleEntity>
  suspend fun getSummary(date: String): SummaryEntity?
}

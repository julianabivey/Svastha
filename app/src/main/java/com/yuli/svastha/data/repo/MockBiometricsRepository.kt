package com.yuli.svastha.data.repo

import com.yuli.svastha.data.db.*
import com.yuli.svastha.data.net.SvasthaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockBiometricsRepository @Inject constructor(
  private val api: SvasthaApi,
  private val db: SvasthaDb
) : BiometricsRepository {

  override suspend fun refresh(): Result<Unit> = withContext(Dispatchers.IO) {
    runCatching {
      val hr = api.heartRateSeries()
      val rr = api.respiratoryRateSeries()
      val sum = api.summary()

      db.sampleDao().clearMetric(hr.metric); db.sampleDao().insertAll(
        hr.samples.map { SampleEntity(metric = hr.metric, ts = it.ts, value = it.value) }
      )
      db.sampleDao().clearMetric(rr.metric); db.sampleDao().insertAll(
        rr.samples.map { SampleEntity(metric = rr.metric, ts = it.ts, value = it.value) }
      )
      db.summaryDao().upsert(
        SummaryEntity(
          date = sum.date,
          hrMin = sum.heartRate["min"] ?: 0.0f,
          hrAvg = sum.heartRate["avg"] ?: 0.0f,
          hrMax = sum.heartRate["max"] ?: 0.0f,
          hrRest = sum.heartRate["resting"] ?: 0.0f,
          rrMin = sum.respiratoryRate["min"] ?: 0.0f,
          rrAvg = sum.respiratoryRate["avg"] ?: 0.0f,
          rrMax = sum.respiratoryRate["max"] ?: 0.0f,
        )
      )
    }
  }

  override suspend fun getHeartSeries() = db.sampleDao().forMetric("heartRateBpm")
  override suspend fun getRespSeries()  = db.sampleDao().forMetric("respiratoryRateBpm")
  override suspend fun getSummary(date: String) = db.summaryDao().forDate(date)
}

package com.yuli.svastha.data.db
import androidx.room.*

@Dao interface SampleDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertAll(samples: List<SampleEntity>)
  @Query("DELETE FROM samples WHERE metric = :metric") suspend fun clearMetric(metric: String)
  @Query("SELECT * FROM samples WHERE metric = :metric ORDER BY ts ASC") suspend fun forMetric(metric: String): List<SampleEntity>
}

@Dao interface SummaryDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsert(summary: SummaryEntity)
  @Query("SELECT * FROM daily_summary WHERE date = :date LIMIT 1") suspend fun forDate(date: String): SummaryEntity?
}

package com.yuli.svastha.data.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "samples")
data class SampleEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val metric: String, // "heartRateBpm" | "respiratoryRateBpm"
  val ts: String,
  val value: Double
)

@Entity(tableName = "daily_summary")
data class SummaryEntity(
  @PrimaryKey val date: String,
  val hrMin: Double, val hrAvg: Double, val hrMax: Double, val hrRest: Double,
  val rrMin: Double, val rrAvg: Double, val rrMax: Double
)

package com.yuli.svastha.data.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "samples")
data class SampleEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val metric: String, // "heartRateBpm" | "respiratoryRateBpm"
  val ts: String,
  val value: Float
)

@Entity(tableName = "daily_summary")
data class SummaryEntity(
  @PrimaryKey val date: String,
  val hrMin: Float, val hrAvg: Float, val hrMax: Float, val hrRest: Float,
  val rrMin: Float, val rrAvg: Float, val rrMax: Float
)

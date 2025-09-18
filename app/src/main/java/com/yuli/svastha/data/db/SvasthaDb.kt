package com.yuli.svastha.data.db
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SampleEntity::class, SummaryEntity::class], version = 1)
abstract class SvasthaDb : RoomDatabase() {
  abstract fun sampleDao(): SampleDao
  abstract fun summaryDao(): SummaryDao
}

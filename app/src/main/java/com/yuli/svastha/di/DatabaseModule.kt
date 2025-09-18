package com.yuli.svastha.di
import android.content.Context
import androidx.room.Room
import com.yuli.svastha.data.db.SvasthaDb
import com.yuli.svastha.data.repo.BiometricsRepository
import com.yuli.svastha.data.repo.MockBiometricsRepository
import dagger.Module; import dagger.Provides; import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Provides @Singleton fun db(@ApplicationContext ctx: Context): SvasthaDb =
    Room.databaseBuilder(ctx, SvasthaDb::class.java, "svastha.db").build()

  @Provides @Singleton fun repo(impl: MockBiometricsRepository): BiometricsRepository = impl
}

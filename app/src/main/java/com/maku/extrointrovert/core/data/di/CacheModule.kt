package com.maku.extrointrovert.core.data.di

import android.content.Context
import androidx.room.Room
import com.maku.extrointrovert.core.data.local.ExtroIntroVertDB
import com.maku.extrointrovert.core.data.local.cache.Cache
import com.maku.extrointrovert.core.data.local.cache.RoomCache
import com.maku.extrointrovert.core.data.local.daos.ExtroIntroVertDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache // inject interface implementations

  companion object {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ExtroIntroVertDB {
      return Room.databaseBuilder(
          context,
          ExtroIntroVertDB::class.java,
          "extroIntroVertDB.db"
      )
          .build()
    }

    @Provides
    fun provideExtroIntroVertDao(
        extroIntroVertDB: ExtroIntroVertDB
    ): ExtroIntroVertDao = extroIntroVertDB.extroIntroVertDao()
  }
}
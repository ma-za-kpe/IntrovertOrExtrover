package com.maku.extrointrovert.core.data.di

import com.maku.extrointrovert.core.data.repository.Repo
import com.maku.extrointrovert.core.data.repository.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.disposables.CompositeDisposable

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

  @Binds
  @ActivityRetainedScoped
  abstract fun bindQuestionsRepository(repository: RepoImpl): Repo

  companion object {
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()
  }
}
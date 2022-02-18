package net.jahez.jahezchallenge.di

import net.jahez.jahezchallenge.features.home.data.MainRepositoryImpl
import net.jahez.jahezchallenge.features.home.domain.MainRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {
  @Singleton @Binds
  abstract fun bindMainRepository(mainRepository: MainRepositoryImpl?): MainRepository?
}
package net.jahez.jahezchallenge.di

import androidx.lifecycle.ViewModel
import net.jahez.jahezchallenge.di.annotitions.ViewModelKey
import net.jahez.jahezchallenge.features.home.domain.GetRestaurantsListUseCase
import net.jahez.jahezchallenge.features.home.ui.viewmodels.MainViewModel
import net.jahez.jahezchallenge.utils.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class ViewModelModule {

  @Provides
  fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
    return ViewModelFactory(providerMap)
  }

  @Provides @IntoMap @ViewModelKey(MainViewModel::class)
  fun provideMainViewModel(
    getRestaurantsListUseCase: GetRestaurantsListUseCase
  ): ViewModel {
    return MainViewModel(getRestaurantsListUseCase)
  }
}
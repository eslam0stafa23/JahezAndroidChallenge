package net.jahez.jahezchallenge.di

import dagger.Component
import net.jahez.jahezchallenge.features.home.ui.activities.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
  modules = [ViewModelModule::class, RepositoriesModule::class, WebServiceModule::class]
)
interface AppComponent {

  fun inject(mainActivity: MainActivity)

  @Component.Factory
  interface Factory {
    fun create(): AppComponent
  }
}
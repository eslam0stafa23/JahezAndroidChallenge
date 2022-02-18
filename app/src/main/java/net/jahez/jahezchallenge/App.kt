package net.jahez.jahezchallenge

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import net.jahez.jahezchallenge.di.AppComponent
import net.jahez.jahezchallenge.di.DaggerAppComponent

class App : Application() {
  lateinit var appComponent: AppComponent
    private set

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.factory().create()
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
  }
}
package net.jahez.jahezchallenge.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import net.jahez.jahezchallenge.App
import net.jahez.jahezchallenge.di.AppComponent
import kotlin.math.round

val Context.app: App get() = applicationContext as App

val AppCompatActivity.appComponent: AppComponent get() = this.app.appComponent

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)

fun Double.round(decimals: Int): Double {
  var multiplier = 1.0
  repeat(decimals) { multiplier *= 10 }
  return round(this * multiplier) / multiplier
}
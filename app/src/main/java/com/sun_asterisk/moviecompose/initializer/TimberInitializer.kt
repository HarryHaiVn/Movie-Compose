@file:Suppress("unused")

package com.sun_asterisk.moviecompose.initializer

import android.content.Context
import androidx.startup.Initializer
import com.sun_asterisk.moviecompose.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
      Timber.d("TimberInitializer is initialized.")
    }
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
    SandwichInitializer::class.java
  )
}

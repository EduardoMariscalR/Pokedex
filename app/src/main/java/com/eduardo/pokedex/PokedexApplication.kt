package com.eduardo.pokedex

import android.app.Application
import com.eduardo.pokedex.di.AppContainer
import com.eduardo.pokedex.di.DefaultAppContainer

class PokedexApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
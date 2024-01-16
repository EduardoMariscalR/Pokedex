package com.eduardo.pokedex

import android.app.Application
import com.eduardo.pokedex.data.AppContainer
import com.eduardo.pokedex.data.DefaultAppContainer

class PokedexPokemonApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
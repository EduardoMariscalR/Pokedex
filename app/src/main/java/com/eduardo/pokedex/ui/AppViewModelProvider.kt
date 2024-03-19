package com.eduardo.pokedex.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.eduardo.pokedex.PokedexApplication
import com.eduardo.pokedex.ui.home.HomeViewModel
import com.eduardo.pokedex.ui.home.PokemonDetailViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(pokedexApplication().container.pokedexPokemonRepository)
        }
        initializer {
            PokemonDetailViewModel(
                this.createSavedStateHandle(),
                pokedexApplication().container.pokedexPokemonRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [PokedexApplication].
 */

fun CreationExtras.pokedexApplication(): PokedexApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication)
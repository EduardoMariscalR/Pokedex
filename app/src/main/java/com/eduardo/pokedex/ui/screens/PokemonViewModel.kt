package com.eduardo.pokedex.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.eduardo.pokedex.PokedexPokemonApplication
import com.eduardo.pokedex.data.PokedexPokemonRepository
import com.eduardo.pokedex.model.PokemonModel
import com.eduardo.pokedex.model.Sprites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed interface PokemonUiState {
    data class Success(val pokemon: List<PokemonModel>) : PokemonUiState
    data object Error : PokemonUiState
    data object Loading : PokemonUiState
}

class PokemonViewModel(
    private val pokedexPokemonRepository: PokedexPokemonRepository
) : ViewModel() {
    var pokemonUistate: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
        private set

    init {
        getFirstPokemon()
    }

//    fun getPokemonList(){
//        viewModelScope.launch {
//            pokemonUistate = PokemonUiState.Loading
//            pokemonUistate = try {
//                PokemonUiState.Success(pokedexPokemonRepository.getPokemonList())
//            } catch (e: IOException) {
//                PokemonUiState.Error
//            } catch (e: HttpException) {
//                PokemonUiState.Error
//            }
//        }
//    }

    fun getFirstPokemon() {
        viewModelScope.launch {
            pokemonUistate = PokemonUiState.Loading
            val listPokemon = getPokemonAllInfo()
            if (listPokemon.contains(
                    PokemonModel(
                        0,
                        "Error",
                        0,
                        0,
                        emptyList(),
                        Sprites(""),
                        emptyList(),
                        emptyList()
                    )
                )
            ) {
                pokemonUistate = PokemonUiState.Error
            } else {
                pokemonUistate = PokemonUiState.Success(listPokemon.toList())
            }
//            pokemonUistate = try {
//                val result: MutableList<PokemonModel> = mutableListOf()
//                for (i in 1..25) {
//                    val pokemon = async {
//                        pokedexPokemonRepository.getPokemonInfo(i)
//                    }
//                    result.add(
//                        pokemon.await()
//                    )
//                }
//                PokemonUiState.Success(result.toList())
//                PokemonUiState.Success(pokedexPokemonRepository.getPokemonInfo(1))
//            } catch (e: IOException) {
//                PokemonUiState.Error
//            } catch (e: HttpException) {
//                PokemonUiState.Error
//            }
        }
    }

    private suspend fun getPokemonAllInfo(): List<PokemonModel> {
        val result: MutableList<PokemonModel> = mutableListOf()
        withContext(Dispatchers.IO) {
            for (i in 1..50) {
                val pokemon = async {
                    try {
                        pokedexPokemonRepository.getPokemonInfo(i.toString())
                    } catch (e: IOException) {
                        PokemonModel(
                            0,
                            "Error",
                            0,
                            0,
                            emptyList(),
                            Sprites(""),
                            emptyList(),
                            emptyList()
                        )
                    } catch (e: HttpException) {
                        PokemonModel(
                            0,
                            "Error",
                            0,
                            0,
                            emptyList(),
                            Sprites(""),
                            emptyList(),
                            emptyList()
                        )
                    }
                }
                Log.i("Eduardo", "Pokemon = $i")
                result.add(
                    pokemon.await()
                )
            }
        }
        return result
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexPokemonApplication)
                val pokedexPokemonRepository = application.container.pokedexPokemonRepository
                PokemonViewModel(pokedexPokemonRepository = pokedexPokemonRepository)
            }
        }
    }


}

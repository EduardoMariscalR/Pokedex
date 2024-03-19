package com.eduardo.pokedex.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardo.pokedex.data.network.PokedexPokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface PokemonUiState {
    data class Success(val pokemon: StateFlow<List<PokemonCardDataUi>>) : PokemonUiState
    data object Error : PokemonUiState
    data object Loading : PokemonUiState
}

class HomeViewModel(
    private val pokedexPokemonRepository: PokedexPokemonRepository
) : ViewModel() {
    var homeUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
        private set

    var filterTypeString: String = ""

    private val pokemons = MutableStateFlow(emptyList<PokemonCardDataUi>())
    private val _filteredPokemons = MutableStateFlow(emptyList<PokemonCardDataUi>())
    private val filteredPokemons = _filteredPokemons.asStateFlow()

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            homeUiState = PokemonUiState.Loading
            homeUiState =
                try {
                    val response = pokedexPokemonRepository.getAllPokemonCardItemsStream()
                    pokemons.value = if(response.isEmpty()){ throw IOException() } else {response}
                    _filteredPokemons.value = pokemons.value
                    PokemonUiState.Success(filteredPokemons)
                } catch (e: IOException) {
                    PokemonUiState.Error
                } catch (e: HttpException) {
                    PokemonUiState.Error
                }
        }
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun setFilterType(filterType: String) {
        this.filterTypeString = filterType
    }


    private var count: Int = 0
    fun filterPokemon(text: String, sortOrder: String) {
        if (text.isNotEmpty() && filterTypeString.isEmpty()) {
            _filteredPokemons.value = sortPokemon(sortOrder, searchPokemon(text, pokemons.value))
        } else if (text.isEmpty() && filterTypeString.isNotEmpty()) {
            _filteredPokemons.value =
                sortPokemon(sortOrder, filterType(filterTypeString, pokemons.value))
        } else if (text.isNotEmpty() && filterTypeString.isNotEmpty()) {
            _filteredPokemons.value = sortPokemon(
                sortOrder,
                searchPokemon(text, filterType(filterTypeString, pokemons.value))
            )
        } else {
            _filteredPokemons.value = sortPokemon(sortOrder, pokemons.value)
        }

        count++
        Log.i("Eduardo", "Se filtra $count")
        homeUiState = PokemonUiState.Success(filteredPokemons)
    }


    private fun sortPokemon(text: String, data: List<PokemonCardDataUi>): List<PokemonCardDataUi> {
        return when {
            (text == "NumericDescending") -> data.sortedBy { it.id }.reversed()
            (text == "AlphabeticalAscending") -> data.sortedBy { it.name }
            (text == "AlphabeticalDescending") -> data.sortedBy { it.name }.reversed()
            else -> data.sortedBy { it.id }
        }
    }

    private fun searchPokemon(text: String, data: List<PokemonCardDataUi>): List<PokemonCardDataUi> {
        return data.filter {
            it.name.startsWith(
                text,
                ignoreCase = true
            ) || it.id.toString().startsWith(
                text,
                ignoreCase = true
            )
        }
    }

    private fun filterType(text: String, data: List<PokemonCardDataUi>): List<PokemonCardDataUi> {
        return data.filter {
            it.type1
                .equals(
                    text,
                    ignoreCase = true
                ) || it.type2
                ?.equals(
                    text,
                    ignoreCase = true
                ) ?: false
        }
    }
}
    data class PokemonCardDataUi(
        val id: Int,
        val name: String,
        val type1: String,
        val type2: String?,
        val favorite: Boolean
    )

package com.eduardo.pokedex.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardo.pokedex.data.network.PokedexPokemonRepository
import com.eduardo.pokedex.data.network.model.pokemonDetail.Pokemon
import com.eduardo.pokedex.data.network.model.species.PokemonSpecies
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface PokemonDetailUiState {
    data class Success(val pokemon: Pokemon, val pokemonSpecies: PokemonSpecies) : PokemonDetailUiState
    data object Error : PokemonDetailUiState
    data object Loading : PokemonDetailUiState
}
class PokemonDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val pokedexPokemonRepository: PokedexPokemonRepository
) : ViewModel() {
    var pokemonDetailUiState: PokemonDetailUiState by mutableStateOf(PokemonDetailUiState.Loading)
        private set

    private val pokemonId: Int = checkNotNull(savedStateHandle[PokemonDetailDestination.pokemonIdArg])

    init {
        getPokemon(pokemonId)
    }

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch {
            pokemonDetailUiState = PokemonDetailUiState.Loading
            pokemonDetailUiState =
                try {
                    PokemonDetailUiState.Success(pokedexPokemonRepository.getPokemonDetail(pokemonId), pokedexPokemonRepository.getPokemonSpecies(pokemonId))
                } catch (e: IOException){
                    PokemonDetailUiState.Error
                } catch (e: HttpException) {
                    PokemonDetailUiState.Error
                }
        }
    }
}
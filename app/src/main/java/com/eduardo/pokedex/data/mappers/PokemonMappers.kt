package com.eduardo.pokedex.data.mappers

import com.eduardo.pokedex.data.local.model.ListItem
import com.eduardo.pokedex.data.local.model.PokemonCardItem
import com.eduardo.pokedex.data.local.model.TypeItem
import com.eduardo.pokedex.data.network.model.NamedApiResource
import com.eduardo.pokedex.data.network.model.PokemonCardData
import com.eduardo.pokedex.data.network.model.type.Type
import com.eduardo.pokedex.data.network.model.type.TypeRelations
import com.eduardo.pokedex.ui.home.PokemonCardDataUi

fun PokemonCardData.toPokemonCardItem(): PokemonCardItem = PokemonCardItem(
    id = id,
    name = name,
    type1 = type1!!,
    type2 = type2,
    favorite = favorite
)

fun PokemonCardItem.toPokemonCardDataUi(): PokemonCardDataUi = PokemonCardDataUi(
    id = id,
    name = name,
    type1 = type1,
    type2 = type2,
    favorite = favorite
)

val normal = NamedApiResource(name = "normal", url = "https://pokeapi.co/api/v2/type/1/")
val fighting = NamedApiResource(name = "fighting", url = "https://pokeapi.co/api/v2/type/2/")
val flying = NamedApiResource(name = "flying", url = "https://pokeapi.co/api/v2/type/3/")
val poison = NamedApiResource(name = "poison", url = "https://pokeapi.co/api/v2/type/4/")
val ground = NamedApiResource(name = "ground", url = "https://pokeapi.co/api/v2/type/5/")
val rock = NamedApiResource(name = "rock", url = "https://pokeapi.co/api/v2/type/6/")
val bug = NamedApiResource(name = "bug", url = "https://pokeapi.co/api/v2/type/7/")
val ghost = NamedApiResource(name = "ghost", url = "https://pokeapi.co/api/v2/type/8/")
val steel = NamedApiResource(name = "steel", url = "https://pokeapi.co/api/v2/type/9/")
val fire = NamedApiResource(name = "fire", url = "https://pokeapi.co/api/v2/type/10/")
val water = NamedApiResource(name = "water", url = "https://pokeapi.co/api/v2/type/11/")
val grass = NamedApiResource(name = "grass", url = "https://pokeapi.co/api/v2/type/12/")
val electric = NamedApiResource(name = "electric", url = "https://pokeapi.co/api/v2/type/13/")
val psychic = NamedApiResource(name = "psychic", url = "https://pokeapi.co/api/v2/type/14/")
val ice = NamedApiResource(name = "ice", url = "https://pokeapi.co/api/v2/type/15/")
val dragon = NamedApiResource(name = "dragon", url = "https://pokeapi.co/api/v2/type/16/")
val dark = NamedApiResource(name = "dark", url = "https://pokeapi.co/api/v2/type/17/")
val fairy = NamedApiResource(name = "fairy", url = "https://pokeapi.co/api/v2/type/18/")


fun Type.toTypeItem(): TypeItem = TypeItem(
    id = id,
    name = name,
    // ? Normal
    damageToNormal = damageTo(normal,damageRelations),
    damageFromNormal = damageFrom(normal,damageRelations),
    // ? Fighting
    damageToFighting = damageTo(fighting,damageRelations),
    damageFromFighting = damageFrom(fighting,damageRelations),
    // ? Flying
    damageToFlying = damageTo(flying,damageRelations),
    damageFromFlying = damageFrom(flying,damageRelations),
    // ? Poison
    damageToPoison = damageTo(poison,damageRelations),
    damageFromPoison = damageFrom(poison,damageRelations),
    // ? Ground
    damageToGround = damageTo(ground,damageRelations),
    damageFromGround = damageFrom(ground,damageRelations),
    // ? Rock
    damageToRock = damageTo(rock,damageRelations),
    damageFromRock = damageFrom(rock,damageRelations),
    // ? Bug
    damageToBug = damageTo(bug,damageRelations),
    damageFromBug = damageFrom(bug,damageRelations),
    // ? Ghost
    damageToGhost = damageTo(ghost,damageRelations),
    damageFromGhost = damageFrom(ghost,damageRelations),
    // ? Steel
    damageToSteel = damageTo(steel,damageRelations),
    damageFromSteel = damageFrom(steel,damageRelations),
    // ? Fire
    damageToFire = damageTo(fire,damageRelations),
    damageFromFire = damageFrom(fire,damageRelations),
    // ? Water
    damageToWater = damageTo(water,damageRelations),
    damageFromWater = damageFrom(water,damageRelations),
    // ? Grass
    damageToGrass = damageTo(grass,damageRelations),
    damageFromGrass = damageFrom(grass,damageRelations),
    // ? Electric
    damageToElectric = damageTo(electric,damageRelations),
    damageFromElectric = damageFrom(electric,damageRelations),
    // ? Psychic
    damageToPsychic = damageTo(psychic,damageRelations),
    damageFromPsychic = damageFrom(psychic,damageRelations),
    // ? Ice
    damageToIce = damageTo(ice,damageRelations),
    damageFromIce = damageFrom(ice,damageRelations),
    // ? Dragon
    damageToDragon = damageTo(dragon,damageRelations),
    damageFromDragon = damageFrom(dragon,damageRelations),
    // ? Dark
    damageToDark = damageTo(dark,damageRelations),
    damageFromDark = damageFrom(dark,damageRelations),
    // ? Fairy
    damageToFairy = damageTo(fairy,damageRelations),
    damageFromFairy = damageFrom(fairy,damageRelations),
    )

fun damageTo(type: NamedApiResource, damageRelations: TypeRelations): Double {
    return if(damageRelations.noDamageTo.contains(type)){
        0.0
    } else if (damageRelations.halfDamageTo.contains(type)){
        0.5
    } else if (damageRelations.doubleDamageTo.contains(type)){
        2.0
    } else{
        1.0
    }
}

fun damageFrom(type: NamedApiResource, damageRelations: TypeRelations): Double {
    return if(damageRelations.noDamageFrom.contains(type)){
        0.0
    } else if (damageRelations.halfDamageFrom.contains(type)){
        0.5
    } else if (damageRelations.doubleDamageFrom.contains(type)){
        2.0
    } else{
        1.0
    }
}

fun NamedApiResource.toPokemonCardData(): PokemonCardData = PokemonCardData(
    id = urlToId(url),
    name = name,
    type1 = null,
    type2 = null,
    favorite = false
)

fun NamedApiResource.toListItem(): ListItem = ListItem(
    url = url,
    name = name,
    id = urlToId(url),
    category = urlToCat(url)
)

private fun urlToId(url: String): Int {
    return "/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isDigit() || it == '-' }.toInt()
}

private fun urlToCat(url: String): String {
    return "/[a-z\\-]+/-?[0-9]+/$".toRegex().find(url)!!.value.filter { it.isLetter() || it == '-' }
}
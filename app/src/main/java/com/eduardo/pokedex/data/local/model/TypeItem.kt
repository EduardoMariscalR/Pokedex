package com.eduardo.pokedex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type_items")
data class TypeItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    // ? Normal
    val damageToNormal: Double = 1.0,
    val damageFromNormal: Double = 1.0,
    // ? Fighting
    val damageToFighting: Double = 1.0,
    val damageFromFighting: Double = 1.0,
    // ? Flying
    val damageToFlying: Double = 1.0,
    val damageFromFlying: Double = 1.0,
    // ? Poison
    val damageToPoison: Double = 1.0,
    val damageFromPoison: Double = 1.0,
    // ? Ground
    val damageToGround: Double = 1.0,
    val damageFromGround: Double = 1.0,
    // ? Rock
    val damageToRock: Double = 1.0,
    val damageFromRock: Double = 1.0,
    // ? Bug
    val damageToBug: Double = 1.0,
    val damageFromBug: Double = 1.0,
    // ? Ghost
    val damageToGhost: Double = 1.0,
    val damageFromGhost: Double = 1.0,
    // ? Steel
    val damageToSteel: Double = 1.0,
    val damageFromSteel: Double = 1.0,
    // ? Fire
    val damageToFire: Double = 1.0,
    val damageFromFire: Double = 1.0,
    // ? Water
    val damageToWater: Double = 1.0,
    val damageFromWater: Double = 1.0,
    // ? Grass
    val damageToGrass: Double = 1.0,
    val damageFromGrass: Double = 1.0,
    // ? Electric
    val damageToElectric: Double = 1.0,
    val damageFromElectric: Double = 1.0,
    // ? Psychic
    val damageToPsychic: Double = 1.0,
    val damageFromPsychic: Double = 1.0,
    // ? Ice
    val damageToIce: Double = 1.0,
    val damageFromIce: Double = 1.0,
    // ? Dragon
    val damageToDragon: Double = 1.0,
    val damageFromDragon: Double = 1.0,
    // ? Dark
    val damageToDark: Double = 1.0,
    val damageFromDark: Double = 1.0,
    // ? Fairy
    val damageToFairy: Double = 1.0,
    val damageFromFairy: Double = 1.0,
)
package com.example.pokeapp.data.mapper

import com.example.pokeapp.data.dto.PokeResultDto
import com.example.pokeapp.data.dto.StatDto
import com.example.pokeapp.data.dto.TypeDto
import com.example.pokeapp.data.dto.SpritesDto
import com.example.pokeapp.domain.model.PokeItem
import com.example.pokeapp.domain.model.Stat
import com.example.pokeapp.domain.model.StatList
import com.example.pokeapp.domain.model.Type
import com.example.pokeapp.domain.model.TypeList
import com.example.pokeapp.domain.model.Sprites
import com.example.pokeapp.domain.model.Other
import com.example.pokeapp.domain.model.Home

fun PokeResultDto.toPokeItem(): PokeItem {
    return PokeItem(
        height = height ?: 0,
        id = id ?: 0,
        name = name ?: "",
        order = order ?: 0,
        sprites = sprites?.toSprites() ?: Sprites(Other(Home("", "", "", ""))),
        stats = stats?.map {
            it.toStat()
        } ?: emptyList(),
        types = types?.map {
            it.toType()
        } ?: emptyList(),
        weight = weight ?: 0,
    )
}

fun StatDto.toStat(): Stat {
    return Stat(
        base_stat = base_stat ?: 0,
        effort = effort ?: 0,
        stat = listOf(
            stat?.let {
                StatList(
                    name = it.name ?: "",
                    url = it.url ?: ""
                )
            } ?: StatList("", "")
        )
    )
}

fun TypeDto.toType(): Type {
    return Type(
        slot = slot ?: 0,
        Type = listOf(
            type?.let {
                TypeList(
                    name = it.name ?: "",
                    url = it.url ?: ""
                )
            } ?: TypeList("", "")
        )
    )
}

fun SpritesDto.toSprites(): Sprites {
    return Sprites(
        other = Other(
            home = Home(
                front_default = other?.home?.front_default ?: "",
                front_female = other?.home?.front_female ?: "",
                front_shiny = other?.home?.front_shiny ?: "",
                front_shiny_female = other?.home?.front_shiny_female ?: ""
            )
        )
    )
}

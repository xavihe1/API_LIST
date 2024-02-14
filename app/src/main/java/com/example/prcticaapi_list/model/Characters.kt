package com.example.prcticaapi_list.model

data class Characters(
    val abilities: List<Ability>,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val role: Role,
)
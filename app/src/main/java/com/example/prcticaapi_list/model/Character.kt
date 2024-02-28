package com.example.prcticaapi_list.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterEntity", foreignKeys = @ForeignKey(entity = Character.class, parentColumns = "id",
    childColumns = "displayName", onDelete = ForeignKey.CASCADE))
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val abilities: List<Ability>,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val role: Role
)
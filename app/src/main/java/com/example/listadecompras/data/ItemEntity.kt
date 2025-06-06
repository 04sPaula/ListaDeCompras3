package com.example.listadecompras.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)

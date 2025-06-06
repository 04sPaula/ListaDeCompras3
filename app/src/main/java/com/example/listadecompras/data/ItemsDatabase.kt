package com.example.listadecompras.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listadecompras.data.ItemEntity
import com.example.listadecompras.data.ItemsDao

@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemsDatabase: RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
}
package com.example.listadecompras

import com.example.listadecompras.data.ItemEntity

data class ItemModel(
    val id: Long,
    val name: String,
    val onRemove: (ItemModel) -> Unit
)
fun ItemEntity.toModel(onRemove: (ItemModel) -> Unit): ItemModel
{
    return ItemModel(
        id = this.id,
        name = this.name,
        onRemove = onRemove
    )
}

fun ItemModel.toEntity(): ItemEntity {
    return ItemEntity(
        id = this.id,
        name = this.name
    )
}
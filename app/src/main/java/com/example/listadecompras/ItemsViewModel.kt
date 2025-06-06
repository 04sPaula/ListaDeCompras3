package com.example.listadecompras

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.data.ItemsDatabase
import com.example.listadecompras.data.ItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(private val database: ItemsDatabase) : ViewModel() {

    private val _itemsLiveData = MutableLiveData<List<ItemModel>>()
    val itemsLiveData: LiveData<List<ItemModel>> = _itemsLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAll()
        }
    }

    fun addItem(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = ItemEntity(id = 0, name = name)
            database.itemsDao().insert(entity)
            fetchAll()
        }
    }

    private suspend fun fetchAll() {
        val result = database.itemsDao().getAll().map {
            it.toModel(onRemove = ::removeItem)
        }
        _itemsLiveData.postValue(result)
    }

    private fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = item.toEntity()
            database.itemsDao().delete(entity)
            fetchAll()
        }
    }
}

package com.example.prcticaapi_list.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prcticaapi_list.api.Repository
import com.example.prcticaapi_list.model.Character
import com.example.prcticaapi_list.model.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class APIViewModel: ViewModel() {
    private val repository = Repository()

    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _isFavorite = MutableLiveData(false)
    val isFavorite = _isFavorite
    private val _favorites = MutableLiveData<MutableList<Character>>()
    val favorites = _favorites
    private val _characters = MutableLiveData<List<Data>>()
    val characters = _characters
    private val _searchText = MutableLiveData<String>()
    val searchText = _searchText
    private val _status = MutableLiveData(false)
    val status = _status

    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _characters.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error: ", response.message())
                }
            }
        }
    }

    fun getFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = response
                _loading.value = false
            }
        }
    }

    fun isFavorite(character: Character) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.isFavorite(character)
            withContext(Dispatchers.Main) {
                _isFavorite.value = response
            }
        }
    }

    fun saveAsFavorite(character: Character) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveAsFavorite(character)
        }
    }

    fun deleteFavorite(character: Character) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteFavorite(character)
        }
    }



    fun onSearchTextChange(text: String) {

    }

    fun setStatus(value: Boolean) {
        _status.value = value
    }
}
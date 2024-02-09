package com.example.prcticaapi_list.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prcticaapi_list.api.Repository
import com.example.prcticaapi_list.model.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel: ViewModel() {

    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _characters = MutableLiveData<Data>()
    val characters = _characters

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
}
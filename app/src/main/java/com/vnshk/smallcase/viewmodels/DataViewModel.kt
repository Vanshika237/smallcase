package com.vnshk.smallcase.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vnshk.smallcase.api.ApiClient
import com.vnshk.smallcase.datamodels.DummyDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class DataViewModel(app: Application) : AndroidViewModel(app) {
    private val _data = MutableStateFlow<DummyDataModel?>(null)

    val data: StateFlow<DummyDataModel?> get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = ApiClient().getService().fetchData()?.awaitResponse()
                if (response != null) {
                    _data.emit(response.body())
                }
            } catch (e: Exception) {
                Log.e("DataViewModel", "Error fetching data", e)
            }
        }
    }
}
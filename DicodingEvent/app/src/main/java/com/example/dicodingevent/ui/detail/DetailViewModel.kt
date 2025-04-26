package com.example.dicodingevent.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingevent.data.response.EventDetails
import com.example.dicodingevent.data.response.EventData
import com.example.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _event = MutableLiveData<EventData>()
    val event: LiveData<EventData> = _event

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DetailViewModel"
    }

    fun findEvent(eventId: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailEvent(eventId)
        client.enqueue(object : Callback<EventDetails> {
            override fun onResponse(
                call: Call<EventDetails>,
                response: Response<EventDetails>
            ) {
                _isLoading.value = false
                if(response.isSuccessful) {
                    _event.value = response.body()?.event
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventDetails>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
package com.example.dicodingevent.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingevent.data.response.EventResponse
import com.example.dicodingevent.data.response.EventData
import com.example.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _upcomingEvents = MutableLiveData<List<EventData>>()
    val upcomingEvents: LiveData<List<EventData>> = _upcomingEvents

    private val _finishedEvents = MutableLiveData<List<EventData>>()
    val finishedEvents: LiveData<List<EventData>> = _finishedEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "HomeViewModel"
        private const val LIMIT = 5
        private const val INACTIVE = 0
    }

    init {
        getSomeUpcomingEvents()
        getSomeFinishedEvents()
    }

    private fun getSomeUpcomingEvents() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getEvents(limit = LIMIT)
        Log.d("API Debug", "Request URL: ${client.request().url}")
        client.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _upcomingEvents.value = response.body()?.listEvents
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun getSomeFinishedEvents() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getEvents(INACTIVE, LIMIT)
        Log.d("API Debug", "Request URL: ${client.request().url}")
        client.enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _finishedEvents.value = response.body()?.listEvents
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}
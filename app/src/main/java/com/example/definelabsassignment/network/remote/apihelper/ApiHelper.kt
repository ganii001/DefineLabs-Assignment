package com.example.definelabsassignment.network.remote.apihelper


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.definelabsassignment.network.responses.ApiResponse
import com.example.definelabsassignment.network.responses.VenuesItem
import retrofit2.Response
import retrofit2.http.Body

interface ApiHelper {

    suspend fun getVenues(): Response<ApiResponse>

}
package com.example.definelabsassignment.network.remote.repository

import androidx.lifecycle.LiveData
import com.example.definelabsassignment.network.remote.apihelper.ApiHelper
import com.example.definelabsassignment.network.responses.VenuesItem
import retrofit2.http.Body
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getVenues() = apiHelper.getVenues()
}
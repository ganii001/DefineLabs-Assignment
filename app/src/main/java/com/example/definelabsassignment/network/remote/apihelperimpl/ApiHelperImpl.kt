package com.example.definelabsassignment.network.remote.apihelperimpl

import com.example.definelabsassignment.network.remote.apihelper.ApiHelper
import com.example.definelabsassignment.network.remote.apiservice.ApiService
import com.example.definelabsassignment.network.responses.ApiResponse
import com.example.definelabsassignment.network.responses.VenuesItem
import retrofit2.Response
import retrofit2.http.Body
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) :
    ApiHelper {

    override suspend fun getVenues(): Response<ApiResponse> =
        apiService.getVenues()

}
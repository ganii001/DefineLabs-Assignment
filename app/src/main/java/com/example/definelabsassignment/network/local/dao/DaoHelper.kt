package com.example.definelabsassignment.network.local.dao

import com.example.definelabsassignment.network.local.entity.VenuesEntity
import com.example.definelabsassignment.network.responses.VenuesItem

interface DaoHelper {

    suspend fun insertVenueData(venuesTable: VenuesEntity)

    suspend fun deleteVenueData(id: String)

    suspend fun getData(): List<VenuesEntity>

}
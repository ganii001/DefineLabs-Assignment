package com.example.definelabsassignment.network.local.repository

import com.example.definelabsassignment.network.local.dao.DaoHelper
import com.example.definelabsassignment.network.local.entity.VenuesEntity
import com.example.definelabsassignment.network.responses.VenuesItem
import javax.inject.Inject

class DaoRepository @Inject constructor(private val daoHelper: DaoHelper) {

    suspend fun insertVenueData(venuesTable: VenuesEntity) = daoHelper.insertVenueData(venuesTable)

    suspend fun deleteVenueData(id: String) = daoHelper.deleteVenueData(id)

    suspend fun getData(): List<VenuesEntity> = daoHelper.getData()

}
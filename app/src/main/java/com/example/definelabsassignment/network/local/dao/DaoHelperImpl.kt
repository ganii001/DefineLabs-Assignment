package com.example.definelabsassignment.network.local.dao

import com.example.definelabsassignment.network.local.entity.VenuesEntity
import com.example.definelabsassignment.network.responses.VenuesItem
import javax.inject.Inject

class DaoHelperImpl @Inject constructor(private val daoService: DaoService) : DaoHelper {

    override suspend fun insertVenueData(venuesTable: VenuesEntity) =
        daoService.insertVenueData(venuesTable)

    override suspend fun deleteVenueData(id: String) =
        daoService.deleteVenueData(id)

    override suspend fun getData(): List<VenuesEntity> = daoService.getData()


}
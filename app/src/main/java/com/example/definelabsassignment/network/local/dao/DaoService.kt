package com.example.definelabsassignment.network.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.definelabsassignment.network.local.entity.VenuesEntity

@Dao
interface DaoService {

    @Query("SELECT * from VenuesEntity")
    suspend fun getData(): List<VenuesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenueData(venuesTable: VenuesEntity)

    @Query("DELETE FROM VenuesEntity WHERE venue_id = :id")
    suspend fun deleteVenueData(id: String)
}
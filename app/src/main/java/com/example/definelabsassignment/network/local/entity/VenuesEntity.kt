package com.example.definelabsassignment.network.local.entity

import androidx.room.*
import com.example.definelabsassignment.network.responses.Location
import com.example.definelabsassignment.network.responses.VenuesItem

@Entity(tableName ="VenuesEntity" )
data class VenuesEntity(
    val venue_id: String? = null,
    val name: String? = null,
    val address: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


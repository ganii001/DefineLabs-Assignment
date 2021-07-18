package com.example.definelabsassignment.network.responses

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @field:SerializedName("response")
    var response: Response? = null,
)

data class VenuesItem(

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("location")
    var location: Location? = null,

    @field:SerializedName("id")
    var venue_id: String? = null

)

data class Location(

    @field:SerializedName("cc")
    var cc: String? = null,

    @field:SerializedName("country")
    var country: String? = null,

    @field:SerializedName("address")
    var address: String? = null,

    @field:SerializedName("lng")
    var lng: Double? = null,

    @field:SerializedName("distance")
    var distance: Int? = null,

    @field:SerializedName("formattedAddress")
    var formattedAddress: List<String?>? = null,

    @field:SerializedName("city")
    var city: String? = null,

    @field:SerializedName("postalCode")
    var postalCode: String? = null,

    @field:SerializedName("state")
    var state: String? = null,

    @field:SerializedName("crossStreet")
    var crossStreet: String? = null,

    @field:SerializedName("lat")
    var lat: Double? = null,

    @field:SerializedName("neighborhood")
    var neighborhood: String? = null
)

data class Response(

    @field:SerializedName("venues")
    var venues: List<VenuesItem>? = null
)


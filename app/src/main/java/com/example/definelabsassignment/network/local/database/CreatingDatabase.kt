package com.example.definelabsassignment.network.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.definelabsassignment.network.local.dao.DaoService
import com.example.definelabsassignment.network.local.entity.VenuesEntity

@Database(entities = [VenuesEntity::class], version = 1)
abstract class CreatingDatabase : RoomDatabase() {

    abstract fun daoService(): DaoService
}
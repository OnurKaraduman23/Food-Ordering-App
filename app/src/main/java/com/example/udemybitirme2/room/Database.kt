package com.example.udemybitirme2.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.udemybitirme2.data.entity.FavoriYemekler


@Database(entities = [FavoriYemekler::class], version = 2)
abstract class Database : RoomDatabase(){

    abstract fun getFavoriYemeklerDao():FavoriYemeklerDao
}
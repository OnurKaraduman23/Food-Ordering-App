package com.example.udemybitirme2.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.udemybitirme2.data.entity.FavoriYemekler


@Dao
interface FavoriYemeklerDao {

    @Query("SELECT * FROM favoriler")
    suspend fun favoriYemekleriYukle() : List<FavoriYemekler>

    @Insert
    suspend fun ekleFav(favYemek:FavoriYemekler)

    @Query("DELETE FROM favoriler WHERE yemek_id = :yemek_id")
    suspend fun silFav(yemek_id:Int)

    @Query("SELECT COUNT(*) FROM favoriler WHERE yemek_id = :yemekId")
    suspend fun checkIfUrunExists(yemekId: Int) : Int


}
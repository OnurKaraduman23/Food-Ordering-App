package com.example.udemybitirme2.retrofit

import com.example.udemybitirme2.data.entity.YemeklerCevap
import retrofit2.http.GET

interface YemeklerDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle():YemeklerCevap

}
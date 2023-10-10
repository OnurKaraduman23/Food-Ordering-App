package com.example.udemybitirme2.data.repo

import com.example.udemybitirme2.data.datasource.YemeklerDataSource
import com.example.udemybitirme2.data.entity.Yemekler

class YemeklerRepository(var yDso:YemeklerDataSource) {



    suspend fun sil(yemek_id:Int) = yDso.sil(yemek_id)
    suspend fun yemekleriYukle() : List<Yemekler> = yDso.yemekleriListele()
}
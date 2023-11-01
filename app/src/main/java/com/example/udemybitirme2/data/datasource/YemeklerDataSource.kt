package com.example.udemybitirme2.data.datasource

import android.util.Log
import com.example.udemybitirme2.data.entity.SepetYemekler
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var yDao : YemeklerDao) {



    suspend fun yemekleriListele():List<Yemekler> =
        withContext(Dispatchers.IO){
            return@withContext  yDao.yemekleriYukle().yemekler
        }

    suspend fun sepetYukle(kullanici_adi: String) : List<SepetYemekler> =
        withContext(Dispatchers.IO){
            Log.e("Dante","SepetDataSource $kullanici_adi")
            return@withContext yDao.sepetGetir(kullanici_adi).sepet_yemekler
        }
    suspend fun sepetYemekSil(sepet_yemek_id:Int,kullanici_adi: String) = yDao.sepetYemekSil(sepet_yemek_id,kullanici_adi)

    suspend fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String) = yDao.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
}
package com.example.udemybitirme2.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var yDao : YemeklerDao) {



    suspend fun yemekleriListele():List<Yemekler> =
        withContext(Dispatchers.IO){
            return@withContext  yDao.yemekleriYukle().yemekler
        }


    suspend fun sil(yemek_id:Int){
        withContext(Dispatchers.IO){
            Log.e("Dante Yemek Sil","Silinen YemekId $yemek_id")
        }
    }
}
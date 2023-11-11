package com.example.udemybitirme2.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udemybitirme2.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class YemekDetayViewModel @Inject constructor(var yRepo: YemeklerRepository): ViewModel() {

    var urunAdet = MutableLiveData<String>()
    var favState = MutableLiveData<String>()


    init {
        urunAdet = MutableLiveData<String>("1")
        favState = MutableLiveData<String>("0")

    }
    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            yRepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        }
    }

    fun urunAdetArttir(urunAdeti:String){
        urunAdet.value = (urunAdeti.toInt() + 1 ).toString()

    }
    fun urunAdetAzalt(urunAdeti: String){

        if (urunAdeti.toInt() >1){
            urunAdet.value=(urunAdeti.toInt() - 1 ).toString()
        }

    }

    fun favYemekEkle(yemek_id: Int,yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int){
        CoroutineScope(Dispatchers.Main).launch {
            yRepo.favYemekEkle(yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat)


        }
    }

    fun favSil(yemek_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            yRepo.favSil(yemek_id)
        }
    }

    suspend fun getYemekByAdi(yemek_id: Int): Int = withContext(Dispatchers.Main) {
        val result = CoroutineScope(Dispatchers.IO).async {
            yRepo.getYemekByAdi(yemek_id)
        }.await()
        return@withContext result
    }


}
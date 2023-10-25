package com.example.udemybitirme2.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.udemybitirme2.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class YemekDetayViewModel @Inject constructor(var yRepo: YemeklerRepository): ViewModel() {

    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
//            Log.e("Dante","Yemekad:$yemek_adi - yemekresAd: $yemek_resim_adi - yemekFiyat: $yemek_fiyat - yemekSipAdet: $yemek_siparis_adet, kullanıcıadı: $kullanici_adi")
            yRepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        }
    }
}
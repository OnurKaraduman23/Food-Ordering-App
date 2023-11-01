package com.example.udemybitirme2.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udemybitirme2.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class YemekDetayViewModel @Inject constructor(var yRepo: YemeklerRepository): ViewModel() {

    var urunAdet = MutableLiveData<String>()


    init {
        urunAdet = MutableLiveData<String>("1")


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


}
package com.example.udemybitirme2.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udemybitirme2.data.entity.SepetYemekler
import com.example.udemybitirme2.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var yRepo:YemeklerRepository): ViewModel() {


    var sepetListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        sepetYukle("Dante")
    }

    fun sepetYukle(kullaniciAdi:String){
        CoroutineScope(Dispatchers.Main).launch {
            try {

                sepetListesi.value=yRepo.sepetYukle(kullaniciAdi)

            }catch (e:Exception){
                Log.e("Dante","SepetViewModel başarısız $kullaniciAdi")
            }
        }
    }

    fun sepetYemekSil(sepet_yemek_id:Int,kullaniciAdi: String){
        CoroutineScope(Dispatchers.Main).launch {
            yRepo.sepetYemeksil(sepet_yemek_id,kullaniciAdi)
            sepetYukle("Dante")
        }
    }
}
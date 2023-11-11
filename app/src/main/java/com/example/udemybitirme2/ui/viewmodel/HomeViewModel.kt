package com.example.udemybitirme2.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udemybitirme2.data.entity.FavoriYemekler
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.Exception


@HiltViewModel
class HomeViewModel @Inject constructor(var yRepo:YemeklerRepository) : ViewModel () {


    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var favoriYemeklerListesi = MutableLiveData<List<FavoriYemekler>>()

    init {
        yemekleriYukle()
        favoriYemekleriYukle()
    }
    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                yemeklerListesi.value = yRepo.yemekleriYukle()
            }catch (e:Exception){

            }
        }
    }

    fun favoriYemekleriYukle(){

        CoroutineScope(Dispatchers.Main).launch {
            favoriYemeklerListesi.value = yRepo.favoriYemekleriYukle()
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
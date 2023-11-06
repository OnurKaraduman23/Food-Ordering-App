package com.example.udemybitirme2.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udemybitirme2.data.entity.FavoriYemekler
import com.example.udemybitirme2.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavorilerViewModel@Inject constructor(var yRepo: YemeklerRepository) : ViewModel() {


    var favoriYemeklerListesi = MutableLiveData<List<FavoriYemekler>>()

    init {
        favoriYemekleriYukle()
    }
    fun favoriYemekleriYukle(){

        CoroutineScope(Dispatchers.Main).launch {
            favoriYemeklerListesi.value = yRepo.favoriYemekleriYukle()
        }
    }


    fun sil(yemek_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            yRepo.favSil(yemek_id)
            favoriYemekleriYukle()
        }
    }
}
package com.example.udemybitirme2.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception


@HiltViewModel
class HomeViewModel @Inject constructor(var yRepo:YemeklerRepository) : ViewModel () {


    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()

    }
    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                yemeklerListesi.value = yRepo.yemekleriYukle()
            }catch (e:Exception){

            }
        }
    }



}
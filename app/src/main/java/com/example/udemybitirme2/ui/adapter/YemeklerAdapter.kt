package com.example.udemybitirme2.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.datasource.YemeklerDataSource
import com.example.udemybitirme2.data.entity.FavoriYemekler
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.data.repo.YemeklerRepository
import com.example.udemybitirme2.util.gecisYap
import com.example.udemybitirme2.databinding.CardYemekTasarimBinding
import com.example.udemybitirme2.ui.fragment.AnasayfaFragmentDirections
import com.example.udemybitirme2.ui.viewmodel.FavorilerViewModel
import com.example.udemybitirme2.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class YemeklerAdapter(var homeViewModel: HomeViewModel):RecyclerView.Adapter<YemeklerAdapter.CardTasarimNesneleriniTutucu>() {

    private var yemeklerListesi : MutableList<Yemekler> = mutableListOf()

    inner class CardTasarimNesneleriniTutucu(var tasarim:CardYemekTasarimBinding):RecyclerView.ViewHolder(tasarim.root){

        fun bind(yemek: Yemekler){
            var favSayisi : Int? = 0
            var fav = false
            var tus = false
            tasarim.yemekNesnesi = yemek

            CoroutineScope(Dispatchers.Main).launch {
                favSayisi =getFavoriSayisi(yemek.yemek_id)
                yemek.yemek_adi
                if (favSayisi!! > 0) fav = true else fav = false
                if (fav){
                    tasarim.imageViewFavoriButton.setImageResource(R.drawable.ic_favorite_dolu)
                }else{
                    tasarim.imageViewFavoriButton.setImageResource(R.drawable.ic_favorite_bos)
                }
            }

//            if (favSayisi!! > 0) fav = true else fav = false
            Log.e("Dante Adapter ","yemkAdi:${yemek.yemek_adi} - favSayisi: $favSayisi - fav:$fav")


            val yemeklerUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(tasarim.root.context).load(yemeklerUrl).override(500,750).into(tasarim.imageViewYemek)
            tasarim.imageView.setOnClickListener {
                Snackbar.make(it,"${yemek.yemek_adi} Sepete Eklendi", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLUE)
                    .setActionTextColor(Color.RED)
                    .show()
            }


            tasarim.imageViewYemek.setOnClickListener {
                val gecis = AnasayfaFragmentDirections.yemekDetayFragmentGecis(yemek)
                Navigation.gecisYap(it,gecis)
            }

            tasarim.imageViewFavoriButton.setOnClickListener{
                tus = !tus
                if (tus == false){
                    tasarim.imageViewFavoriButton.setImageResource(R.drawable.ic_favorite_bos)
                    homeViewModel.sil(yemek.yemek_id)
                }
                else{
                    homeViewModel.favYemekEkle(yemek.yemek_id,yemek.yemek_adi,yemek.yemek_resim_adi,yemek.yemek_fiyat)
                    tasarim.imageViewFavoriButton.setImageResource(R.drawable.ic_favorite_dolu)
                }

            }
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneleriniTutucu {
        val binding : CardYemekTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.card_yemek_tasarim,parent,false)
        return CardTasarimNesneleriniTutucu(binding)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimNesneleriniTutucu, position: Int) {
      holder.bind(yemeklerListesi.get(position))

    }

    fun submitList(yemekler:List<Yemekler>){
        yemeklerListesi.clear()
        yemeklerListesi.addAll(yemekler)
        this.notifyDataSetChanged()
    }

    suspend fun getFavoriSayisi(yemek_id:Int): Int {
        return homeViewModel.getYemekByAdi(yemek_id)
    }

}


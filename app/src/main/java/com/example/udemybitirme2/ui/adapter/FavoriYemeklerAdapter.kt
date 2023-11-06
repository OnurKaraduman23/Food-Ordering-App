package com.example.udemybitirme2.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.entity.FavoriYemekler
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.databinding.CardYemekTasarimBinding
import com.example.udemybitirme2.databinding.FavYemekCardTasarimBinding
import com.example.udemybitirme2.ui.fragment.AnasayfaFragmentDirections
import com.example.udemybitirme2.ui.viewmodel.FavorilerViewModel
import com.example.udemybitirme2.ui.viewmodel.HomeViewModel
import com.example.udemybitirme2.util.gecisYap
import com.google.android.material.snackbar.Snackbar

class FavoriYemeklerAdapter(var favoriYemeklerListesi:List<FavoriYemekler>,var viewModel:FavorilerViewModel):RecyclerView.Adapter<FavoriYemeklerAdapter.FavoriYemeklerHolder>() {


    inner class FavoriYemeklerHolder(var tasarim:FavYemekCardTasarimBinding):RecyclerView.ViewHolder(tasarim.root){
        fun bind(yemek : FavoriYemekler){

            tasarim.yemekNesnesi = yemek

            val yemeklerUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(tasarim.root.context).load(yemeklerUrl).override(500,750).into(tasarim.imageViewYemek)
            tasarim.imageView.setOnClickListener {
                Snackbar.make(it,"${yemek.yemek_adi} Sepete Eklendi", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLUE)
                    .setActionTextColor(Color.RED)
                    .show()

            }

//            tasarim.imageViewYemek.setOnClickListener {
//                val gecis = AnasayfaFragmentDirections.yemekDetayFragmentGecis(yemek)
//                Navigation.gecisYap(it,gecis)
//            }

            tasarim.imageViewFavoriButton.setOnClickListener{
                viewModel.sil(yemek.yemek_id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriYemeklerHolder {
        val binding : FavYemekCardTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.fav_yemek_card_tasarim,parent,false)
        return FavoriYemeklerHolder(binding)
    }

    override fun getItemCount(): Int {
        return favoriYemeklerListesi.size
    }

    override fun onBindViewHolder(holder: FavoriYemeklerHolder, position: Int) {
        holder.bind(favoriYemeklerListesi.get(position))
    }


}
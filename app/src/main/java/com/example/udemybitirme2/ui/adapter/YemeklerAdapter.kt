package com.example.udemybitirme2.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.databinding.CardYemekTasarimBinding
import com.example.udemybitirme2.ui.fragment.AnasayfaFragmentDirections
import com.example.udemybitirme2.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter(var mContext:Context, var yemeklerListesi:List<Yemekler>,var viewModel: HomeViewModel):RecyclerView.Adapter<YemeklerAdapter.CardTasarimNesneleriniTutucu>() {

    inner class CardTasarimNesneleriniTutucu(var tasarim:CardYemekTasarimBinding):RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimNesneleriniTutucu {
        val binding : CardYemekTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.card_yemek_tasarim,parent,false)
        return CardTasarimNesneleriniTutucu(binding)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimNesneleriniTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim

        t.yemekNesnesi = yemek


        val yemeklerUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext).load(yemeklerUrl).override(500,750).into(t.imageViewYemek)
        t.imageView.setOnClickListener {
            Snackbar.make(it,"${yemek.yemek_adi} Sepete Eklendi", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLUE)
                .setActionTextColor(Color.RED)
                .show()

        }

        t.imageViewYemek.setOnClickListener {

            val gecis = AnasayfaFragmentDirections.yemekDetayFragmentGecis(yemek)
            Navigation.findNavController(it).navigate(gecis)

        }
    }


}
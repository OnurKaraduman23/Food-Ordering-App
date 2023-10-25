package com.example.udemybitirme2.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.entity.SepetYemekler
import com.example.udemybitirme2.databinding.SepetCardTasarimBinding
import com.example.udemybitirme2.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar

class SepetAdapter(var mContext:Context, var sepetListesi:List<SepetYemekler>,var viewModel:SepetViewModel) : RecyclerView.Adapter<SepetAdapter.DetayCardNesneleriniTutucu>() {

    inner class DetayCardNesneleriniTutucu(var tasarim:SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetayCardNesneleriniTutucu {
        val binding :SepetCardTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.sepet_card_tasarim,parent,false)
        return DetayCardNesneleriniTutucu(binding)
    }

    override fun getItemCount(): Int {
        return sepetListesi.size
    }

    override fun onBindViewHolder(holder: DetayCardNesneleriniTutucu, position: Int) {
        val sepet = sepetListesi.get(position)
        val t = holder.tasarim

        t.textViewFiyatSepet.text = sepet.yemek_fiyat.toString()
        t.textViewAdetSepet.text = sepet.yemek_siparis_adet.toString()
        t.textViewYemekAdiSepet.text = sepet.yemek_adi
        t.textViewAdetxFiyat.text = (sepet.yemek_fiyat * sepet.yemek_siparis_adet).toString() + " ₺"
        Log.e("Dante","${sepet.kullanici_adi} - ${sepet.yemek_adi}")
        val yemeklerUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
        Glide.with(mContext).load(yemeklerUrl).override(500,750).into(t.imageViewYemekResimSepet)

        t.imageViewSilSepet.setOnClickListener{
            Snackbar.make(it,"${sepet.yemek_adi} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.GRAY)
                .setTextColor(Color.RED)
                .setActionTextColor(Color.BLUE)
                .setAction("EVET"){
                    viewModel.sepetYemekSil(sepet.sepet_yemek_id,"Dante")
                }

                .show()
        }
    }
}
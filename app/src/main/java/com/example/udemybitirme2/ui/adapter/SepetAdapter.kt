package com.example.udemybitirme2.ui.adapter


import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.entity.SepetYemekler
import com.example.udemybitirme2.databinding.SepetCardTasarimBinding
import com.example.udemybitirme2.ui.fragment.DialogFragment
import com.example.udemybitirme2.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar

class SepetAdapter(var viewModel:SepetViewModel,var frgManager:FragmentManager) : RecyclerView.Adapter<SepetAdapter.DetayCardNesneleriniTutucu>() {

    private var sepetListesi : MutableList<SepetYemekler> = mutableListOf()
    var toplam = 0
    inner class DetayCardNesneleriniTutucu(var tasarim:SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){

        fun bind(sepet:SepetYemekler){
            tasarim.sepetYemeklerNesnesi = sepet
            val urunAdetliFiyat =  (sepet.yemek_fiyat * sepet.yemek_siparis_adet)

            tasarim.sepetYemeklerNesnesi = sepet
            toplam = urunAdetliFiyat + toplam
            tasarim.yemekTotalFiyat = "$urunAdetliFiyat ₺"
            Log.e("Dante","${sepet.kullanici_adi} - ${sepet.yemek_adi}")

            viewModel.sepetTutariLiveData.value = toplam.toString()

            val yemeklerUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
            Glide.with(tasarim.root.context).load(yemeklerUrl).override(500,750).into(tasarim.imageViewYemekResimSepet)

            tasarim.imageViewSilSepet.setOnClickListener{
                Snackbar.make(it,"${sepet.yemek_adi} silinsin mi?", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.GRAY)
                    .setTextColor(Color.RED)
                    .setActionTextColor(Color.BLUE)
                    .setAction("EVET"){
                        if (sepetListesi.size == 1){
                            viewModel.sepetListesi.value = emptyList()
                            viewModel.sepetTutariLiveData.value = "0"
                        }
                        viewModel.sepetYemekSil(sepet.sepet_yemek_id,"Dante")
                        viewModel.sepetYukle("Dante")
                        val dialog = DialogFragment()
                        val dataToPass = "deleted"
                        dialog.setData(dataToPass)
                        dialog.show(frgManager,"Dialog")
                    }

                    .show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetayCardNesneleriniTutucu {
        val binding :SepetCardTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.sepet_card_tasarim,parent,false)
        return DetayCardNesneleriniTutucu(binding)
    }

    override fun getItemCount(): Int {

        return sepetListesi.size
    }

    override fun onBindViewHolder(holder: DetayCardNesneleriniTutucu, position: Int) {

        holder.bind(sepetListesi.get(position))
    }

    fun submitList(sepet:List<SepetYemekler>){
        sepetListesi.addAll(sepet)
    }
}
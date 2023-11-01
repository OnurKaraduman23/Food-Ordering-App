package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.udemybitirme2.R
import com.example.udemybitirme2.databinding.FragmentYemekDetayBinding
import com.example.udemybitirme2.ui.viewmodel.YemekDetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {

    private lateinit var binding:FragmentYemekDetayBinding
    private lateinit var viewModel:YemekDetayViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay,container,false)
        binding.yemekDetayFragmentNesnesi = this

        binding.kullaniciAdi = "Dante"
        val bundle : YemekDetayFragmentArgs by navArgs()
        val yemek = bundle.yemekler
        binding.yemeklerNesnesi = yemek
        binding.urunTotalFiyat = binding.textViewYemekFiyat.text.toString()

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(this).load(url).override(4000,5500).into(binding.imgYemekGorsel)

        binding.textViewYemekAdi.text = yemek.yemek_adi
//        binding.textViewYemekHakkinda.text = "${yemek.yemek_fiyat} ₺"

        viewModel.urunAdet.observe(viewLifecycleOwner){
            binding.urunAdet = it
            binding.urunTotalFiyat = (it.toInt() * yemek.yemek_fiyat).toString() + " ₺"

        }





        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : YemekDetayViewModel by viewModels()
        viewModel = tempViewModel
    }



    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:String,kullanici_adi:String){

        viewModel.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet.toInt(),kullanici_adi)
        val dialog = DialogFragment()
        val dataToPass = "sepete_eklendi"
        dialog.setData(dataToPass)
        dialog.show(parentFragmentManager,"Dialog")
    }

    fun urunAdetArttir(alinanUrunAdeti:String){
       viewModel.urunAdetArttir(alinanUrunAdeti)
    }
    fun urunAdetAzalt(alinanUrunAdeti:String){

        viewModel.urunAdetAzalt(alinanUrunAdeti)

    }





}
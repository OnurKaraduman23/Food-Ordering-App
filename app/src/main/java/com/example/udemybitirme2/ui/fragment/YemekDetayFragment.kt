package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.udemybitirme2.R
import com.example.udemybitirme2.databinding.FragmentYemekDetayBinding


class YemekDetayFragment : Fragment() {

    private lateinit var binding:FragmentYemekDetayBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay,container,false)


//        val bundle : YemekDetayFragmentArgs by navArgs()
//        val yemek = bundle.yemekler
//
//        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
//        Glide.with(this).load(url).override(4000,5500).into(binding.imageViewYemekDetay)
        return binding.root
    }


}
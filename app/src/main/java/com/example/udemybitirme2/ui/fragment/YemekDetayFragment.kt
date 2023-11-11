package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.udemybitirme2.R
import com.example.udemybitirme2.databinding.FragmentYemekDetayBinding
import com.example.udemybitirme2.ui.viewmodel.YemekDetayViewModel
import com.example.udemybitirme2.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {

    private lateinit var binding:FragmentYemekDetayBinding
    private lateinit var viewModel:YemekDetayViewModel
    private var favoriteState : Int = 0
    private var booleanFavState : Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay,container,false)
        binding.yemekDetayFragmentNesnesi = this




        binding.kullaniciAdi = "Dante"
        val bundle : YemekDetayFragmentArgs by navArgs()
        val yemek = bundle.yemekler
        binding.yemeklerNesnesi = yemek
        binding.urunTotalFiyat = yemek.yemek_fiyat.toString()

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.favState.value = viewModel.getYemekByAdi(yemek.yemek_id).toString()
            Log.e("Dante","${yemek.yemek_adi} favoriteState:$favoriteState")
        }
        viewModel.favState.observe(viewLifecycleOwner){
            if (viewModel.favState.value!!.toInt() > 0){
                binding.imgToolbarBtnFav.setImageResource(R.drawable.ic_fav_fill)

            }else{
                binding.imgToolbarBtnFav.setImageResource(R.drawable.ic_fav_unfill)

            }
        }



        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(this).load(url).override(4000,5500).into(binding.imgYemekGorsel)

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

    fun back(it:View){
        Navigation.gecisYap(it,R.id.yemekDetayAnasayfaGecis)
    }

    fun addFavorite(yemek_id:Int,yemek_adi:String,yemek_resim_adi: String,yemek_fiyat: Int){
        if (booleanFavState){
            viewModel.favSil(yemek_id)
            binding.imgToolbarBtnFav.setImageResource(R.drawable.ic_fav_unfill)
            booleanFavState = false

        }
        else{
            viewModel.favYemekEkle(yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat)
            binding.imgToolbarBtnFav.setImageResource(R.drawable.ic_fav_fill)
            booleanFavState = true

        }



    }


}
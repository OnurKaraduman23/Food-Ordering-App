package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.entity.Yemekler
import com.example.udemybitirme2.databinding.FragmentFavorilerBinding
import com.example.udemybitirme2.ui.adapter.FavoriYemeklerAdapter
import com.example.udemybitirme2.ui.adapter.YemeklerAdapter
import com.example.udemybitirme2.ui.viewmodel.FavorilerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavorilerFragment : Fragment() {

    private lateinit var binding : FragmentFavorilerBinding
    private lateinit var viewModel : FavorilerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(layoutInflater,R.layout.fragment_favoriler,container,false)
        viewModel.favoriYemeklerListesi.observe(viewLifecycleOwner){
            val adapter = FavoriYemeklerAdapter(it,viewModel)
            binding.adapter = adapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FavorilerViewModel by viewModels()
        viewModel = tempViewModel
    }




}
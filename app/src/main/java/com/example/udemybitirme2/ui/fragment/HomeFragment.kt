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
import com.example.udemybitirme2.databinding.ActivityMainBinding
import com.example.udemybitirme2.databinding.FragmentHomeBinding
import com.example.udemybitirme2.ui.adapter.YemeklerAdapter
import com.example.udemybitirme2.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel:HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.homeFragmentNesnesi=this

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){yemekler->
            val adapter = YemeklerAdapter(requireContext(),yemekler,viewModel)
            binding.recyclerView2.adapter = adapter
        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomeViewModel by viewModels()
        viewModel = tempViewModel
    }




}
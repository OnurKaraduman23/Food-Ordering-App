package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.udemybitirme2.R
import com.example.udemybitirme2.databinding.FragmentSepetBinding
import com.example.udemybitirme2.ui.adapter.SepetAdapter
import com.example.udemybitirme2.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding : FragmentSepetBinding
    private lateinit var viewModel : SepetViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_sepet,container,false)
        binding.sepetFragmentNesnesi  = this

        viewModel.sepetListesi.observe(viewLifecycleOwner){
            val sepetAdapter = SepetAdapter(requireContext(),it,viewModel)
//            Log.e("Dante","it ${it[0]}")
            binding.myAdapter = sepetAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SepetViewModel by viewModels()
        viewModel = tempViewModel
    }









}
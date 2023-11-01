package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
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
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_sepet,container,false)
        binding.sepetFragmentNesnesi  = this

        viewModel.sepetListesi.observe(viewLifecycleOwner){
                val sepetAdapter = SepetAdapter(requireContext(),it,viewModel,parentFragmentManager)
                binding.myAdapter = sepetAdapter
        }
        viewModel.sepetTutariLiveData.observe(viewLifecycleOwner){
            binding.textViewTT = "$it ₺"
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SepetViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun sepetiOnayla(){
        viewModel.sepetOnayla()
        animasyonGoster()
    }

    fun animasyonGoster(){
        val dialog = DialogFragment()
        val dataToPass = "siparis_verildi"
        dialog.setData(dataToPass)
        dialog.show(parentFragmentManager,"Dialog")
    }




}
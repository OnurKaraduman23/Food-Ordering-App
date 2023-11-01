package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.entity.Yemekler

import com.example.udemybitirme2.databinding.FragmentHomeBinding
import com.example.udemybitirme2.ui.adapter.YemeklerAdapter
import com.example.udemybitirme2.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel:HomeViewModel
    private lateinit var aramaListesi:List<Yemekler>
    private val adapter:YemeklerAdapter by lazy { YemeklerAdapter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.homeFragmentNesnesi=this
        binding.recyclerView2.adapter = adapter

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){yemekler->
            adapter.submitList(yemekler)
            aramaListesi = yemekler
        }

        binding.searchView.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        val filteredList = aramaListesi.filter { it.yemek_adi.lowercase().contains(s.toString().lowercase()) }
                        adapter.submitList(filteredList)



            }

            override fun afterTextChanged(s: Editable?) {

            }


        })




//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                // Arama metni değiştiğinde bu bölüm çalışır.
//                val filteredList = mutableListOf<Yemekler>()
//                for (yemek in aramaListesi) {
//                    // Yemek adı aramasını burada gerçekleştirin.
//                    if (yemek.yemek_adi.contains(query, ignoreCase = true)) {
//                        filteredList.add(yemek)
//                        Log.e("Dante","$filteredList")
//                        val adapter = YemeklerAdapter(requireContext(), filteredList, viewModel)
//                        binding.recyclerView2.adapter = adapter
//                    }
//                }
//
//
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                // Arama metni değiştiğinde bu bölüm çalışır.
//
//                val filteredList = mutableListOf<Yemekler>()
//                for (yemek in aramaListesi) {
//                    // Yemek adı aramasını burada gerçekleştirin.
//                    if (yemek.yemek_adi.contains(newText, ignoreCase = true)) {
//                        filteredList.add(yemek)
//                        val adapter = YemeklerAdapter(requireContext(), filteredList, viewModel)
//                        Log.e("Dante","${yemek.yemek_adi}")
//                        binding.recyclerView2.adapter = adapter
//                    }
//                }
//                return true
//            }
//        })



        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomeViewModel by viewModels()
        viewModel = tempViewModel
    }




}



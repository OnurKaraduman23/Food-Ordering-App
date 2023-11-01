package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.util.gecisYap
import com.example.udemybitirme2.databinding.FragmentAnasayfaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var fragmentManager : FragmentManager
    lateinit var binding: FragmentAnasayfaBinding
    lateinit var it:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,false)

        it = binding.fab
        binding.fragmentAnsayayfaNesnesi = this
        binding.bottomNavigationView.background = null

        binding.bottomNavigationView.setOnItemSelectedListener {item ->

            when(item.itemId){
                R.id.action_home -> openFragment(HomeFragment())
                R.id.action_kampanya -> openFragment(ProfileFragment())
                R.id.action_favori -> openFragment(FavorilerFragment())
                R.id.action_restaurant -> openFragment(RestoranFragment())
            }
            true
        }
        fragmentManager = childFragmentManager
        openFragment(HomeFragment())

        return binding.root
    }

    fun fabButtonSepet(it:View){
        Navigation.gecisYap(it,R.id.sepetGecis)
    }

    private fun openFragment(fragment:Fragment){
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navHostFragment,fragment)
        fragmentTransaction.commit()
    }





}



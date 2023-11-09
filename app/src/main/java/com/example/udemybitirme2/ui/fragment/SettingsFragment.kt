package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.udemybitirme2.R
import com.example.udemybitirme2.databinding.FragmentSettingsBinding
import com.example.udemybitirme2.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding : FragmentSettingsBinding
    private val languages = ArrayList<String>()
    private lateinit var veriAdapter:ArrayAdapter<String>
    private lateinit var it:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_settings,container,false)

        it = binding.circleImageView2

        binding.switchDarkMode.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }

        languages.add("")
        languages.add("tr")
        languages.add("US")
        veriAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,android.R.id.text1,languages)
        binding.spinner.adapter = veriAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, indeks: Int, p3: Long) {
                val selectedLanguage = languages[indeks]
                if (indeks > 0) {
                    Log.e("Dante","indeks = $indeks - ${languages[indeks]}")
                    changeLanguage(Locale(selectedLanguage))

                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }



        return binding.root
    }


    fun changeLanguage(locale: Locale) {
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        // Fragment'ı yeniden oluştur
        Navigation.gecisYap(it,R.id.anasayfaFragment)


    }








}
package com.example.udemybitirme2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.udemybitirme2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var it:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        it = binding.toolbar

        val sharedPref = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)

        if (isFirstTime) {
            binding.toolbar.visibility = View.GONE
            Log.e("Dante","Onboarding Ekranı")

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.onboardingGecis)

            // Bayrağı güncelle
            with(sharedPref.edit()) {
                putBoolean("isFirstTime", false)
                apply()
            }
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navigationView,navHostFragment.navController)

        val toggle = ActionBarDrawerToggle(this,binding.drawer,binding.toolbar,0,0)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onBackPressed() {

        if (binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }

    }

    fun setToolbarVisibility(isVisible: Boolean) {
        binding.toolbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }



}
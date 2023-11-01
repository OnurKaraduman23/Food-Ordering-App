package com.example.udemybitirme2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.onboarding.OnBoardingItem
import com.example.udemybitirme2.databinding.OnboardingScreenBinding

class OnboardingScreenAdapter():RecyclerView.Adapter<OnboardingScreenAdapter.OnboardingItemViewHolder>() {

    private val onBoardingItems :  MutableList<OnBoardingItem> = mutableListOf()
    inner class OnboardingItemViewHolder(var tasarim : OnboardingScreenBinding) : RecyclerView.ViewHolder(tasarim.root){
        fun bind(onBoarding:OnBoardingItem){
            tasarim.onBoardingNesnesi = onBoarding
            tasarim.imageOnboarding.setAnimation(onBoarding.onboaringImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val binding : OnboardingScreenBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.onboarding_screen,parent,false)
        return OnboardingItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItems.get(position))
    }
    fun submitList(onBoarding:List<OnBoardingItem>){
        onBoardingItems.addAll(onBoarding)
    }
}
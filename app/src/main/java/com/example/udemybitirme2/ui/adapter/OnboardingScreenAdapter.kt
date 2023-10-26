package com.example.udemybitirme2.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.onboarding.OnBoardingItem
import com.example.udemybitirme2.databinding.OnboardingScreenBinding

class OnboardingScreenAdapter(private val mContext: Context, private val onBoardingItem : List<OnBoardingItem>):RecyclerView.Adapter<OnboardingScreenAdapter.OnboardingItemViewHolder>() {

    inner class OnboardingItemViewHolder(var tasarim : OnboardingScreenBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val binding : OnboardingScreenBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext)
            , R.layout.onboarding_screen,parent,false)
        return OnboardingItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return onBoardingItem.size
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        val onBoardingItems = onBoardingItem.get(position)
        val t = holder.tasarim

        t.imageOnboarding.setAnimation(onBoardingItems.onboaringImage)
        t.textTitle.text = onBoardingItems.title
        t.textDescription.text = onBoardingItems.description
    }

}
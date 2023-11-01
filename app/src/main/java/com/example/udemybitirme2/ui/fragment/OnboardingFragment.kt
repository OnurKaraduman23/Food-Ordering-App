package com.example.udemybitirme2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.udemybitirme2.MainActivity
import com.example.udemybitirme2.R
import com.example.udemybitirme2.data.onboarding.OnBoardingItem
import com.example.udemybitirme2.databinding.FragmentOnboardingBinding
import com.example.udemybitirme2.ui.adapter.OnboardingScreenAdapter


class OnboardingFragment : Fragment() {
    private lateinit var binding : FragmentOnboardingBinding
    private  lateinit var onboadringItemsAdapter: OnboardingScreenAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_onboarding,container,false)
        setOnBoardingItems()
        setupIndicators()
        setCurrentIndicator(0)

        binding.buttonGetStarted.setOnClickListener{
            (requireActivity() as MainActivity).setToolbarVisibility(true) // Toolbar'ı görünür yap
            Navigation.findNavController(it).navigate(R.id.anasayfaGecis)

           
        }

        return binding.root
    }

    fun setOnBoardingItems() {
        onboadringItemsAdapter = OnboardingScreenAdapter(requireContext(),
            listOf(
                OnBoardingItem(
                    onboaringImage = R.raw.onboarding1,
                    title = "Manage Your Task" ,
                    description = "Organize all your to do's and projects. Color tah them set priorities and categories"
                ),
                OnBoardingItem(
                    onboaringImage = R.raw.onboarding2,
                    title = "Work On Time" ,
                    description = "When you're overwhelmed by the amount of work you have on your plate, stop and rethink"
                ),
                OnBoardingItem(
                    onboaringImage = R.raw.onboarding3,
                    title = "Get Reminder On Time" ,
                    description = "When you encounter a small task that less than 5 minutes to complete"
                )
            ))

        binding.onboardingViewPager.adapter=onboadringItemsAdapter
        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (binding.onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

    }


    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onboadringItemsAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(requireContext())
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorsContainer.addView(it)
            }
        }

    }

    private fun setCurrentIndicator(position : Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = binding.indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active_background
                    )
                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

}
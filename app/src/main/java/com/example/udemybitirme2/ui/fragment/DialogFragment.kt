package com.example.udemybitirme2.ui.fragment

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.udemybitirme2.R
import com.example.udemybitirme2.databinding.FragmentDialogBinding

class DialogFragment : DialogFragment() {

    private lateinit var binding : FragmentDialogBinding
    private var gelenAnimasyonString : String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_dialog,container,false)

        val animationResourceId = when (gelenAnimasyonString) {
            "sepete_eklendi" -> R.raw.sepete_eklendi
            "favori_eklendi" -> R.raw.favori_eklendi
            "siparis_verildi" -> R.raw.siparis_verildi
            "deleted"->R.raw.deleted

            else -> R.raw.error_anim
        }
        binding.animation.setAnimation(animationResourceId)
        binding.animation.playAnimation()

        binding.animation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }
            override fun onAnimationEnd(animation: Animator) {
                dismiss()
            }
            override fun onAnimationCancel(animation: Animator) {

            }
            override fun onAnimationRepeat(animation: Animator) {
                dismiss()
            }
        })

        return binding.root

    }
    fun setData(data: String) {
       gelenAnimasyonString = data
    }


}
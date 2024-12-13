package com.health.writemylife.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.health.writemylife.R
import com.health.writemylife.ui.home.HomeActivity

class ThirdScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_third_screen, container, false)
        val back = view.findViewById<ImageView>(R.id.iv_btn_backob3)
        back.isClickable = true
        val finish = view.findViewById<Button>(R.id.button_finish_ob3)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)


        finish.setOnClickListener {
            // Menyimpan status onboarding selesai
            onBoardingIsFinished()

            // Navigasi ke HomeActivity
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Menutup activity onboarding
        }


        back.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }

    private fun onBoardingIsFinished(){

        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("finished",true)
        editor.apply()
    }
}
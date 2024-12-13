package com.health.writemylife.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.health.writemylife.R

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second_screen, container, false)

        val next = view.findViewById<TextView>(R.id.button_continue_ob2)
        val back = view.findViewById<ImageView>(R.id.iv_btn_backob2)
        back.isClickable = true
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        next.setOnClickListener {
            viewPager?.currentItem = 2
        }

        back.setOnClickListener {
            viewPager?.currentItem = 0
        }

        return view
    }

}
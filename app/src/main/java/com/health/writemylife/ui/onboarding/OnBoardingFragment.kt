package com.health.writemylife.ui.onboarding

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.health.writemylife.R
import com.health.writemylife.ui.home.HomeActivity
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnBoardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Memaksa orientasi layar menjadi potret
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_boarding, container, false)

        if (onBoardingIsFinished()) {
            // Jika sudah selesai, navigasi ke HomeActivity
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Menutup activity onboarding
            return view // Kembali agar tidak melanjutkan ke bawah
        }

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            childFragmentManager, // Use childFragmentManager here
            lifecycle
        )

        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapter

        val indicator = view.findViewById<WormDotsIndicator>(R.id.dots_indicator)
        indicator.attachTo(viewPager)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Mengembalikan orientasi layar ke pengaturan default
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    private fun onBoardingIsFinished(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished", false)
    }
}
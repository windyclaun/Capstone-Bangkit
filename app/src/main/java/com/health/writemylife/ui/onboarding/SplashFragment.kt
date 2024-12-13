package com.health.writemylife.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.health.writemylife.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Cek apakah fragment sudah ada
//        if (savedInstanceState == null) {
//            Handler(Looper.getMainLooper()).postDelayed({
//                if (onBoardingIsFinished()) {
//                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
//                } else {
//                    findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
//                }
//            }, 2500)
//        }
//
        return view
    }
}

//gajadi dipake

//    private fun onBoardingIsFinished(): Boolean {
//        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
//        return sharedPreferences.getBoolean("finished", false)
//    }
//}
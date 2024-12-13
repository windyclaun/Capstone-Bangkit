package com.health.writemylife.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.health.writemylife.databinding.ActivityHomeBinding
import com.health.writemylife.network.PredictionRequest
import com.health.writemylife.network.RetrofitClient
import com.health.writemylife.ui.result.PositiveResultActivity
import com.health.writemylife.ui.result.ResultActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle button click
        binding.btnAnalyze.setOnClickListener {
            val userStory = binding.etStory.text.toString().trim()
            if (userStory.isEmpty()) {
                Toast.makeText(this, "Mohon isi cerita Anda terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else {
                analyzeStory(userStory)
            }
        }
    }

    private fun analyzeStory(story: String) {
        // Show progress bar
        binding.loadingView.visibility = View.VISIBLE
        binding.tvErrorMessage.visibility = View.GONE

        lifecycleScope.launch {
            try {
                delay(1000)

                Log.d("HomeActivity", "Sending request to server")
                val response = RetrofitClient.apiService.getPrediction(PredictionRequest(story))
                Log.d("HomeActivity", "Received response: $response")

                binding.loadingView.visibility = View.GONE


                if (response.prediction.isNotEmpty() && response.prediction[0][0] > 0.1) {
                    // Navigate to ResultActivity if prediction > 0.1
                    startActivity(Intent(this@HomeActivity, ResultActivity::class.java))
                } else {
                    // Navigate to PositiveResultActivity if prediction <= 0.1
                    startActivity(Intent(this@HomeActivity, PositiveResultActivity::class.java))
                }
            } catch (e: Exception) {
                Log.e("HomeActivity", "Error analyzing story", e)
                binding.loadingView.visibility = View.GONE
                Toast.makeText(this@HomeActivity, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

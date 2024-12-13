package com.health.writemylife.network

import retrofit2.http.Body
import retrofit2.http.POST


interface PredictionApi {
    @POST("/predict")
    suspend fun getPrediction(@Body request: PredictionRequest): PredictionResponse
}
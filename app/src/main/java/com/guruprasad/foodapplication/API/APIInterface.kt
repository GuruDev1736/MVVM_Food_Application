package com.guruprasad.foodapplication.API

import com.guruprasad.foodapplication.Model.Meal
import com.guruprasad.foodapplication.Model.RandomMealResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("random.php")
    fun getRandomMeal() : Call<RandomMealResponseModel>

}
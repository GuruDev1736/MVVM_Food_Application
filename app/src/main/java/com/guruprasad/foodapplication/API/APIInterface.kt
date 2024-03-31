package com.guruprasad.foodapplication.API

import com.guruprasad.foodapplication.Model.CategoryResponseModel
import com.guruprasad.foodapplication.Model.GetMealByIdResponseModel
import com.guruprasad.foodapplication.Model.Meal
import com.guruprasad.foodapplication.Model.PopularMealResponseModel
import com.guruprasad.foodapplication.Model.RandomMealResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("random.php")
    fun getRandomMeal() : Call<RandomMealResponseModel>

    @GET("lookup.php")
    fun getMealById(@Query("i") id : Int) : Call<GetMealByIdResponseModel>

    @GET("filter.php")
    fun getPopularMeal(@Query("c") foodName : String) : Call<PopularMealResponseModel>

    @GET("categories.php")
    fun getCategory() : Call<CategoryResponseModel>


}
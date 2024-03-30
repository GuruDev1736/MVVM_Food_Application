package com.guruprasad.foodapplication.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIUtilities {

    private val BASE_URL ="https://www.themealdb.com/api/json/v1/1/"

    fun getInstance():APIInterface{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)

        return retrofit
    }
}
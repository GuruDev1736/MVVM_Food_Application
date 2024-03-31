package com.guruprasad.foodapplication.ViewModel

import android.R
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guruprasad.foodapplication.API.APIUtilities
import com.guruprasad.foodapplication.Model.Category
import com.guruprasad.foodapplication.Model.CategoryResponseModel
import com.guruprasad.foodapplication.Model.GetMealByIdResponseModel
import com.guruprasad.foodapplication.Model.Meal
import com.guruprasad.foodapplication.Model.MealX
import com.guruprasad.foodapplication.Model.MealXX
import com.guruprasad.foodapplication.Model.PopularMealResponseModel
import com.guruprasad.foodapplication.Model.RandomMealResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {

    private var RandomMealLiveData = MutableLiveData<Meal>()
    private var GetMealByIdLiveData = MutableLiveData<MealX>()
    private var PopularMealLiveData = MutableLiveData<PopularMealResponseModel>()
    private var CategoryLiveData = MutableLiveData<CategoryResponseModel>()

    fun RandomMeal()
    {
        APIUtilities.getInstance().getRandomMeal().enqueue(object :
            Callback<RandomMealResponseModel> {
            override fun onResponse(
                call: Call<RandomMealResponseModel>,
                response: Response<RandomMealResponseModel>
            ) {
                if (response.body()!=null && response.isSuccessful)
                {
                    val randomMeal = response.body()!!.meals.get(0)
                    RandomMealLiveData.postValue(randomMeal)
                }
                else
                {
                    return
                }
            }

            override fun onFailure(call: Call<RandomMealResponseModel>, t: Throwable) {
                   Log.d("GURU",t.message.toString())
            }

        })
    }


    fun GetMealById(id : Int)
    {
        APIUtilities.getInstance().getMealById(id).enqueue(object :
            Callback<GetMealByIdResponseModel> {
            override fun onResponse(
                call: Call<GetMealByIdResponseModel>,
                response: Response<GetMealByIdResponseModel>
            ) {
                if (response.body()!=null && response.isSuccessful)
                {
                    val size = response.body()!!.meals.size-1
                    val meal = response.body()!!.meals.get(size)

                    GetMealByIdLiveData.postValue(meal)
                }
                else
                {
                    return
                }
            }

            override fun onFailure(call: Call<GetMealByIdResponseModel>, t: Throwable) {
                Log.d("GURU",t.message.toString())
            }

        })
    }


    fun PopularMeal(foodName : String)
    {
        APIUtilities.getInstance().getPopularMeal(foodName).enqueue(object :
            Callback<PopularMealResponseModel> {
            override fun onResponse(
                call: Call<PopularMealResponseModel>,
                response: Response<PopularMealResponseModel>
            ) {
                if (response.body()!=null && response.isSuccessful)
                {
                    PopularMealLiveData.postValue(response.body())
                }
                else
                {
                    return
                }
            }

            override fun onFailure(call: Call<PopularMealResponseModel>, t: Throwable) {
                Log.d("GURU",t.message.toString())
            }

        })
    }

    fun Category()
    {
        APIUtilities.getInstance().getCategory().enqueue(object :
            Callback<CategoryResponseModel> {
            override fun onResponse(
                call: Call<CategoryResponseModel>,
                response: Response<CategoryResponseModel>
            ) {
                if (response.body()!=null && response.isSuccessful)
                {
                    CategoryLiveData.postValue(response.body())
                }
                else
                {
                    return
                }
            }

            override fun onFailure(call: Call<CategoryResponseModel>, t: Throwable) {
                Log.d("GURU",t.message.toString())
            }

        })
    }

    fun ObserveRandomMealLiveData():LiveData<Meal>{
        return RandomMealLiveData
    }

    fun ObserveGetMealByIdLiveData():LiveData<MealX>{
        return GetMealByIdLiveData
    }

    fun ObservePopularMealLiveData():LiveData<PopularMealResponseModel>{
        return PopularMealLiveData
    }

    fun ObserveCategoryLiveData():LiveData<CategoryResponseModel>{
        return CategoryLiveData
    }



}
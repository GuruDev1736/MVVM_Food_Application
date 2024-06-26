package com.guruprasad.foodapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guruprasad.foodapplication.DB.MealDatabase
import com.guruprasad.foodapplication.Model.Meal
import kotlinx.coroutines.launch

class MealViewModel(
    val mealDatabase: MealDatabase
) : ViewModel() {

    private var FavLiveData = mealDatabase.mealDao().getAllMeals()

    fun insertMeal(meal : Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().insertAndUpdateMeal(meal)
        }
    }


    fun updateMeal(meal : Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().insertAndUpdateMeal(meal)
        }
    }


    fun deleteMeal(meal: Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().delete(meal)
        }
    }

    fun listMeal(){
        viewModelScope.launch {
            mealDatabase.mealDao().getAllMeals()
        }
    }

    fun ObserveFavLiveData() : LiveData<List<Meal>>{
        return FavLiveData
    }



}
package com.guruprasad.foodapplication.Activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.guruprasad.foodapplication.Activities.Adapters.CategoryMealAdapter
import com.guruprasad.foodapplication.R
import com.guruprasad.foodapplication.ViewModel.HomeViewModel
import com.guruprasad.foodapplication.databinding.ActivityCategoryMealsBinding

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCategoryMealsBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var adapter : CategoryMealAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val categoryName = intent.getStringExtra("categoryName").toString()

        viewModel = ViewModelProvider(this@CategoryMealsActivity).get(HomeViewModel::class.java)
        binding.mealRecyclerview.layoutManager = LinearLayoutManager(this@CategoryMealsActivity)

        viewModel.PopularMeal(categoryName)
        viewModel.ObservePopularMealLiveData().observe(this@CategoryMealsActivity){value->
            adapter = CategoryMealAdapter(this@CategoryMealsActivity,value.meals)
            binding.mealRecyclerview.adapter = adapter
            binding.tvCategoryCount.text = "${categoryName} : ${value.meals.size}"

        }
    }
}
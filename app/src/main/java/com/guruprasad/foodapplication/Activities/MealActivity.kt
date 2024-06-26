package com.guruprasad.foodapplication.Activities


import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.guruprasad.foodapplication.Activities.ui.home.HomeFragment
import com.guruprasad.foodapplication.DB.MealDatabase
import com.guruprasad.foodapplication.Model.Meal
import com.guruprasad.foodapplication.R
import com.guruprasad.foodapplication.ViewModel.HomeViewModel
import com.guruprasad.foodapplication.ViewModel.MealViewModel
import com.guruprasad.foodapplication.ViewModel.MealViewModelFactory
import com.guruprasad.foodapplication.databinding.ActivityMealBinding


class MealActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMealBinding
    private lateinit var mealId : String
    private lateinit var mealName : String
    private lateinit var mealThumb : String
    private lateinit var viewModel : HomeViewModel
    private lateinit var mealViewModel : MealViewModel
    private lateinit var mealDatabase : MealDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealDatabase = MealDatabase.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDatabase)
        viewModel = ViewModelProvider(this@MealActivity).get(HomeViewModel::class.java)
        mealViewModel = ViewModelProvider(this@MealActivity , viewModelFactory).get(MealViewModel::class.java)

        getRandomInformation()
        setInformation()
        LoadingCase()
        viewModel.GetMealById(mealId.toInt())
        ObserveGetMealById()
        Favorite()

    }

    private fun Favorite() {
        binding.btnSave.setOnClickListener{
           MealToSave?.let {
               mealViewModel.insertMeal(it)
               Toast.makeText(this@MealActivity, "Meal Saved Successfully", Toast.LENGTH_SHORT).show()
           }
        }
    }

    private var MealToSave : Meal?=null

    private fun ObserveGetMealById() {
        viewModel.ObserveGetMealByIdLiveData().observe(this@MealActivity){data->

            MealToSave = data

            OnResponseCase()
            binding.info.text = data.strCategory
            binding.location.text = data.strArea
            binding.tvContent.text = data.strInstructions
            binding.imgYoutube.setOnClickListener{
                val intent = Intent()
                    .setAction(Intent.ACTION_VIEW)
                    .setData(Uri.parse(data.strYoutube))
                startActivity(intent)
            }
        }
    }

    private fun setInformation() {
        Glide.with(this@MealActivity)
            .load(mealThumb).into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        val textColor = ContextCompat.getColor(this@MealActivity, R.color.white)
        val colorStateList = ColorStateList.valueOf(textColor)
        binding.collapsingToolbar.setExpandedTitleTextColor(colorStateList)
    }

    private fun getRandomInformation() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID).toString()
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME).toString()
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB).toString()

    }

    private fun LoadingCase()
    {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
        binding.tvContent.visibility = View.INVISIBLE
        binding.mealInfo.visibility = View.INVISIBLE
    }

    private fun OnResponseCase()
    {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
        binding.tvContent.visibility = View.VISIBLE
        binding.mealInfo.visibility = View.VISIBLE

    }
}
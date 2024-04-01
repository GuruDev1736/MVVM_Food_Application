package com.guruprasad.foodapplication.Activities.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guruprasad.foodapplication.Activities.CategoryMealsActivity
import com.guruprasad.foodapplication.Activities.MealActivity
import com.guruprasad.foodapplication.Activities.ui.home.HomeFragment
import com.guruprasad.foodapplication.Model.MealXX
import com.guruprasad.foodapplication.databinding.MealCardBinding

class CategoryMealAdapter(private val context: Context , private val dataList : List<MealXX>) : RecyclerView.Adapter<CategoryMealAdapter.onViewHolder>() {

    class onViewHolder(val binding: MealCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryMealAdapter.onViewHolder {
       val view = MealCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return onViewHolder(view)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: onViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(context).load(dataList.get(position).strMealThumb).into(imgMeal)
            tvMealName.text = dataList.get(position).strMeal
            layout.setOnClickListener{
                context.startActivity(Intent(context,MealActivity::class.java)
                    .putExtra(HomeFragment.MEAL_ID , dataList.get(position).idMeal)
                    .putExtra(HomeFragment.MEAL_NAME , dataList.get(position).strMeal)
                    .putExtra(HomeFragment.MEAL_THUMB , dataList.get(position).strMealThumb)
                )
            }
        }
    }
}
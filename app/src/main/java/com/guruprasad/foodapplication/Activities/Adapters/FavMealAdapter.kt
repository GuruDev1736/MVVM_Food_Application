package com.guruprasad.foodapplication.Activities.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guruprasad.foodapplication.Model.Meal
import com.guruprasad.foodapplication.databinding.MealCardBinding

class FavMealAdapter(private val context: Context , private val datalist : List<Meal>) : RecyclerView.Adapter<FavMealAdapter.onViewHolder>() {

    class onViewHolder(val binding : MealCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): onViewHolder {
       val view = MealCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return onViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: onViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(context).load(datalist.get(position).strMealThumb).into(imgMeal)
            tvMealName.text = datalist.get(position).strMeal
        }
    }
}
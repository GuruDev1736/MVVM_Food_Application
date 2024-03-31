package com.guruprasad.foodapplication.Activities.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guruprasad.foodapplication.Activities.MealActivity
import com.guruprasad.foodapplication.Activities.ui.home.HomeFragment
import com.guruprasad.foodapplication.Model.Category
import com.guruprasad.foodapplication.Model.MealXX
import com.guruprasad.foodapplication.databinding.CategoryItemLayoutBinding
import com.guruprasad.foodapplication.databinding.PopularMealLayoutBinding


class CategoryAdapter(private val context: Context, private val dataList : List<Category>) : RecyclerView.Adapter<CategoryAdapter.onViewHolder>() {

    class onViewHolder(val binding: CategoryItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): onViewHolder {
        val view = CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return onViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: onViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(context).load(dataList.get(position).strCategoryThumb).into(imgCategory)
            tvCategoryName.text = dataList.get(position).strCategory
            }
        }
    }
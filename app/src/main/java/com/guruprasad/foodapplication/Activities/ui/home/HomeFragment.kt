package com.guruprasad.foodapplication.Activities.ui.home

import android.content.Intent
import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.guruprasad.foodapplication.API.APIUtilities
import com.guruprasad.foodapplication.Activities.Adapters.CategoryAdapter
import com.guruprasad.foodapplication.Activities.Adapters.PopularMealAdapter
import com.guruprasad.foodapplication.Activities.MealActivity
import com.guruprasad.foodapplication.Model.Meal
import com.guruprasad.foodapplication.Model.RandomMealResponseModel
import com.guruprasad.foodapplication.ViewModel.HomeViewModel
import com.guruprasad.foodapplication.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel : HomeViewModel
    private lateinit var adapter : PopularMealAdapter
    private lateinit var Categoryadapter : CategoryAdapter
    private val binding get() = _binding!!

    private lateinit var randomMeal : Meal

    companion object{
        const val MEAL_ID = "com.guruprasad.foodapplication.Activities.ui.home.mealId"
        const val MEAL_NAME = "com.guruprasad.foodapplication.Activities.ui.home.mealName"
        const val MEAL_THUMB = "com.guruprasad.foodapplication.Activities.ui.home.mealThumb"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel =  ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewModel.RandomMeal()
        ObserveRandomMeal()

        binding.imgRandomMeal.setOnClickListener{
            startActivity(Intent(requireContext(),MealActivity::class.java)
                .putExtra(MEAL_ID,randomMeal.idMeal)
                .putExtra(MEAL_NAME,randomMeal.strMeal)
                .putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            )
        }

        binding.recViewMealsPopular.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        viewModel.PopularMeal("Seafood")
        ObservePopularMeal()

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL,false)
        viewModel.Category()
        ObserveCategory()




        return root
    }

    private fun ObserveCategory() {
        viewModel.ObserveCategoryLiveData().observe(viewLifecycleOwner){value->

            Categoryadapter = CategoryAdapter(requireContext(),value.categories)
            binding.recyclerView.adapter = Categoryadapter
        }
    }

    private fun ObservePopularMeal() {
        viewModel.ObservePopularMealLiveData().observe(viewLifecycleOwner){value->

            adapter = PopularMealAdapter(requireContext(),value.meals)
            binding.recViewMealsPopular.adapter = adapter
        }
    }

    private fun ObserveRandomMeal() {
        viewModel.ObserveRandomMealLiveData().observe(viewLifecycleOwner
        ) { value ->
            Glide.with(requireContext()).load(value.strMealThumb).into(binding.imgRandomMeal)

            this.randomMeal = value
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
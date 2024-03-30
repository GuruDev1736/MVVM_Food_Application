package com.guruprasad.foodapplication.Activities.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.guruprasad.foodapplication.API.APIUtilities
import com.guruprasad.foodapplication.Model.Meal
import com.guruprasad.foodapplication.Model.RandomMealResponseModel
import com.guruprasad.foodapplication.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        APIUtilities.getInstance().getRandomMeal().enqueue(object : Callback<RandomMealResponseModel>{
            override fun onResponse(
                call: Call<RandomMealResponseModel>,
                response: Response<RandomMealResponseModel>
            ) {
                if (response.body()!=null && response.isSuccessful)
                {
                    val randomMeal = response.body()!!.meals.get(0)
                    Glide.with(requireContext()).load(randomMeal.strMealThumb).into(binding.imgRandomMeal)
                }
                else
                {
                    Toast.makeText(requireContext(),"Response UnSuccessful ${response.errorBody()}",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RandomMealResponseModel>, t: Throwable) {
                Toast.makeText(requireContext(),"Failed to get response ${t.message}",Toast.LENGTH_LONG).show()
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
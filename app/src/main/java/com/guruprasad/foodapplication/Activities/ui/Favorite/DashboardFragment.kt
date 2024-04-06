package com.guruprasad.foodapplication.Activities.ui.Favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.guruprasad.foodapplication.Activities.Adapters.FavMealAdapter
import com.guruprasad.foodapplication.DB.MealDatabase
import com.guruprasad.foodapplication.ViewModel.MealViewModel
import com.guruprasad.foodapplication.ViewModel.MealViewModelFactory
import com.guruprasad.foodapplication.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var viewModel : MealViewModel
    private lateinit var mealDatabase : MealDatabase
    private lateinit var adapter : FavMealAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mealDatabase = MealDatabase.getInstance(requireContext())
        val viewModelFactory = MealViewModelFactory(mealDatabase)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(MealViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        observeFavorite()


        return root
    }

    private fun observeFavorite() {
        viewModel.ObserveFavLiveData().observe(viewLifecycleOwner){
            adapter = FavMealAdapter(requireContext(),it)
            binding.recyclerView.adapter = adapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
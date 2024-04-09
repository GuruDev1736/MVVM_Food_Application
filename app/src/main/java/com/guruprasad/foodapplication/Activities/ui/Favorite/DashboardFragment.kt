package com.guruprasad.foodapplication.Activities.ui.Favorite

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.guruprasad.foodapplication.Activities.Adapters.FavMealAdapter
import com.guruprasad.foodapplication.DB.MealDatabase
import com.guruprasad.foodapplication.ViewModel.MealViewModel
import com.guruprasad.foodapplication.ViewModel.MealViewModelFactory
import com.guruprasad.foodapplication.databinding.FragmentDashboardBinding

class DashboardFragment() : Fragment() {

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

            val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ) = true

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val mealList = it // Assuming it is the list of meals

                    if (mealList.isNotEmpty() && position < mealList.size) {
                        val deletedMeal = mealList[position]
                        viewModel.deleteMeal(deletedMeal)

                        Snackbar.make(requireView(), "Meal Deleted Successfully", Snackbar.LENGTH_LONG)
                            .setAction("UNDO") { _ ->
                                viewModel.insertMeal(deletedMeal)
                            }
                            .show()
                    } else {
                        Log.e("TAG", "Invalid position or meal list is empty")
                    }
                }
            }


            ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.recyclerView)



        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
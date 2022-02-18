package net.jahez.jahezchallenge.features.home.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.databinding.ActivityMainBinding
import net.jahez.jahezchallenge.features.home.data.models.Restaurant
import net.jahez.jahezchallenge.features.home.ui.adapters.RestaurantsAdapter
import net.jahez.jahezchallenge.features.home.ui.viewmodels.MainViewModel
import net.jahez.jahezchallenge.utils.SpinnerUtils
import net.jahez.jahezchallenge.utils.appComponent
import net.jahez.jahezchallenge.utils.result.ResourceType
import net.jahez.jahezchallenge.utils.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  @Inject lateinit var viewModelFactory: ViewModelFactory
  private lateinit var mainViewModel: MainViewModel
  private val restaurantsAdapter: RestaurantsAdapter by lazy { RestaurantsAdapter() }
  private val sortingAdapter: ArrayAdapter<String> by lazy { SpinnerUtils.createArrayAdapter(this) }
  private var restaurantsList: List<Restaurant>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    appComponent.inject(this)
    initViewModel()
    setupRecyclerViews()
    setupSortingSpinner()
    setupActions()
    setupObservers()
  }

  private fun initViewModel() {
    mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
  }

  private fun setupRecyclerViews() {
    binding.rvRestaurants.layoutManager = LinearLayoutManager(this)
    binding.rvRestaurants.adapter = restaurantsAdapter
  }

  private fun setupSortingSpinner() {
    resources.getStringArray(R.array.sorting_value_list).onEach { sortingAdapter.add(it) }
    binding.spinnerSortingList.adapter = sortingAdapter
    binding.spinnerSortingList.onItemSelectedListener =
      object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
          adapterView: AdapterView<*>?,
          view: View,
          position: Int,
          l: Long
        ) {
          when (binding.spinnerSortingList.selectedItem.toString()) {
            getString(R.string.sorting_value_1) -> {}
            getString(R.string.sorting_value_2) -> {
              restaurantsAdapter.submitList(restaurantsList?.sortedBy { it.distance })
            }
            getString(R.string.sorting_value_3) -> {
              restaurantsAdapter.submitList(restaurantsList?.sortedBy { it.rating })
            }
          }
          binding.rvRestaurants.smoothScrollToPosition(0)
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {}
      }
  }

  @SuppressLint("NotifyDataSetChanged")
  private fun setupActions() {
    binding.btnReset.setOnClickListener {
      restaurantsAdapter.submitList(restaurantsList)
      restaurantsAdapter.notifyDataSetChanged()
      binding.spinnerSortingList.setSelection(0)
    }
  }

  private fun setupObservers() {
    mainViewModel.restaurantsList().observe(this, {
      when (it.resourceType) {
        ResourceType.SUCCESS -> {
          setLoading(false)
          if (it.data != null) restaurantsList = it.data
          restaurantsAdapter.submitList(restaurantsList)
        }
        ResourceType.LOADING -> {
          setLoading(true)
        }
      }
    })
  }

  private fun setLoading(isLoading: Boolean) {
    if (isLoading) {
      binding.loadingBar.visibility = View.VISIBLE
    } else {
      binding.loadingBar.visibility = View.GONE
    }
  }

}
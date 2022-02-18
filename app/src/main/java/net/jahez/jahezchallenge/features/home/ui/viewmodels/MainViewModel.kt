package net.jahez.jahezchallenge.features.home.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.jahez.jahezchallenge.features.home.data.models.Restaurant
import net.jahez.jahezchallenge.features.home.domain.GetRestaurantsListUseCase
import net.jahez.jahezchallenge.utils.asMappedResourceLiveData
import net.jahez.jahezchallenge.utils.result.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(
  var getRestaurantsListUseCase: GetRestaurantsListUseCase,
) : ViewModel() {

  fun restaurantsList(): LiveData<Resource<List<Restaurant>>> =
    getRestaurantsListUseCase.get().asMappedResourceLiveData()
}
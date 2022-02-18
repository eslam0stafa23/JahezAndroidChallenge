package net.jahez.jahezchallenge.features.home.domain

import kotlinx.coroutines.flow.Flow
import net.jahez.jahezchallenge.features.home.data.models.Restaurant
import net.jahez.jahezchallenge.utils.result.Resource
import javax.inject.Inject

class GetRestaurantsListUseCase @Inject constructor(private val mainRepository: MainRepository) {

  fun get(): Flow<Resource<List<Restaurant>>> =
    mainRepository.getRestaurantsList()
}
package net.jahez.jahezchallenge.features.home.domain

import kotlinx.coroutines.flow.Flow
import net.jahez.jahezchallenge.features.home.data.models.Restaurant
import net.jahez.jahezchallenge.utils.result.Resource

interface MainRepository {
  fun getRestaurantsList(): Flow<Resource<List<Restaurant>>>
}
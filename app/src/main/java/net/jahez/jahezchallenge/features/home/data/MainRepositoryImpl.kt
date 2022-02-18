package net.jahez.jahezchallenge.features.home.data

import net.jahez.jahezchallenge.features.home.domain.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.jahez.jahezchallenge.features.home.data.models.Restaurant
import net.jahez.jahezchallenge.features.home.data.remote.RestaurantsApiService
import net.jahez.jahezchallenge.utils.result.Resource
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val restaurantsApiService: RestaurantsApiService) :
  MainRepository {
  override fun getRestaurantsList(): Flow<Resource<List<Restaurant>>> {
    return flow {
      emit(Resource.loading())
      try {
        val apiResponse = restaurantsApiService.getRestaurantsList()
        emit(Resource.success(apiResponse))
      } catch (e: IOException) {
        emit(Resource.error())
      }
    }
  }
}


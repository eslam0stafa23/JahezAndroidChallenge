package net.jahez.jahezchallenge.features.home.data.remote

import net.jahez.jahezchallenge.features.home.data.models.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApiService {
  @GET("restaurants.json")
  suspend fun getRestaurantsList(): List<Restaurant>
}
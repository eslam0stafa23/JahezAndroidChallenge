package net.jahez.jahezchallenge.features.home.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.databinding.ItemRestaurantBinding
import net.jahez.jahezchallenge.features.home.data.models.Restaurant
import net.jahez.jahezchallenge.features.home.ui.adapters.RestaurantsAdapter.RestaurantsViewHolder
import net.jahez.jahezchallenge.utils.layoutInflater
import net.jahez.jahezchallenge.utils.round

class RestaurantsAdapter : ListAdapter<Restaurant, RestaurantsViewHolder>(callback) {

  companion object {
    private val callback = object : DiffUtil.ItemCallback<Restaurant>() {
      override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
    val binding = ItemRestaurantBinding.inflate(parent.layoutInflater, parent, false)
    return RestaurantsViewHolder(binding)
  }

  override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class RestaurantsViewHolder(private val binding: ItemRestaurantBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val context: Context = binding.root.context
    fun bind(restaurant: Restaurant) {
      Glide.with(binding.root)
        .load(restaurant.image)
        .placeholder(R.drawable.ic_restaurant_logo_placeholder)
        .into(binding.ivRestaurantLogo)
      binding.tvRestaurantName.text = restaurant.name
      binding.tvRestaurantDescription.text = restaurant.description
      binding.tvRestaurantWorkingHours.text = restaurant.hours
      binding.tvRestaurantDistance.text =
        context.getString(R.string.restaurant_distance, restaurant.distance?.round(1).toString())
      binding.ratingBar.rating = restaurant.rating?.toFloat()!!
      if (restaurant.hasOffer) binding.tvHasOffers.visibility = View.VISIBLE
    }
  }
}
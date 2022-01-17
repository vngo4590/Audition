package com.creatis.audition.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.databinding.FeaturedTrackMetaItemBinding

private const val FEATURED_TRACK_TYPE = 0

class HomeAdapter() : ListAdapter<HomeDataItem, RecyclerView.ViewHolder>(HomeItemDiffCallback()) {

    class FeaturedTrackMetaViewHolder(val binding: FeaturedTrackMetaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrackAndPropertiesAdapter) {
            binding.trackModel = item.trackAndProperties
            binding.executePendingBindings()
        }

        companion object {
            /**
             * Return the binding of the ViewHolder
             * */
            fun from(parent: ViewGroup): FeaturedTrackMetaViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FeaturedTrackMetaItemBinding.inflate(layoutInflater, parent, false)
                return FeaturedTrackMetaViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FEATURED_TRACK_TYPE -> FeaturedTrackMetaViewHolder.from(parent)
            else -> {
                throw ClassCastException("Unknown viewType $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FeaturedTrackMetaViewHolder -> {
                val item: TrackAndPropertiesAdapter = getItem(position) as TrackAndPropertiesAdapter
                holder.bind(item)
            }
            else -> {
                throw ClassCastException("Unknown Holder $holder")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getItemType()
    }
}

sealed interface HomeDataItem {
    fun getItemId() : String
    fun getItemType(): Int
}

class TrackAndPropertiesAdapter(
    val trackAndProperties: TrackAndProperties
) : HomeDataItem {

    override fun getItemType(): Int {
        return FEATURED_TRACK_TYPE
    }

    override fun getItemId(): String {
        return trackAndProperties.track.trackId
    }
}

/**
 * Determine whether a Home Item is the same as each other
 * */
class HomeItemDiffCallback : DiffUtil.ItemCallback<HomeDataItem>() {
    override fun areItemsTheSame(oldItem: HomeDataItem, newItem: HomeDataItem): Boolean {
        return oldItem.getItemType() == newItem.getItemType() && oldItem.getItemId() == newItem.getItemId()
    }

    override fun areContentsTheSame(oldItem: HomeDataItem, newItem: HomeDataItem): Boolean {
        return oldItem == newItem
    }
}
package com.creatis.audition.ui.playtrack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.databinding.FeaturedTrackItemBinding

class PlayTrackAdapter() : ListAdapter<TrackAndProperties, PlayTrackAdapter.PlayTrackViewHolder>(PlayTrackDiffCallback()) {
    class PlayTrackViewHolder private constructor(private val binding: FeaturedTrackItemBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
        * Bind the viewholder with our track information
        * */
        fun bind(item: TrackAndProperties) {
            binding.trackInfo = item
            binding.executePendingBindings()
        }

        companion object {
            /**
            * Return the binding of the ViewHolder
            * */
            fun from(parent: ViewGroup): PlayTrackViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FeaturedTrackItemBinding.inflate(layoutInflater, parent, false)
                return PlayTrackViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayTrackViewHolder {
        return PlayTrackViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PlayTrackViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

/**
* Determine whether a track is the same as each other
* */
class PlayTrackDiffCallback :DiffUtil.ItemCallback<TrackAndProperties> () {

    override fun areItemsTheSame(oldItem: TrackAndProperties, newItem: TrackAndProperties): Boolean {
        return oldItem.track.trackId == newItem.track.trackId
    }

    override fun areContentsTheSame(oldItem: TrackAndProperties, newItem: TrackAndProperties): Boolean {
        return oldItem == newItem && oldItem.share == newItem.share && oldItem.images == newItem.images
    }
}
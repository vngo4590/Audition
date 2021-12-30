package com.creatis.audition.ui.playtrack

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creatis.audition.data.playtrack.playtracklist.Track
import com.creatis.audition.databinding.FeaturedTrackItemBinding

class PlayTrackAdapter() : ListAdapter<Track, PlayTrackAdapter.PlayTrackViewHolder>(PlayTrackDiffCallback()) {
    class PlayTrackViewHolder private constructor(private val binding: FeaturedTrackItemBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
        * Bind the viewholder with our track information
        * */
        fun bind(item: Track) {
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
class PlayTrackDiffCallback :DiffUtil.ItemCallback<Track> () {

    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}
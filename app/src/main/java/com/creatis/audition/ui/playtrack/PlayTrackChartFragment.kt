package com.creatis.audition.ui.playtrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.creatis.audition.databinding.FragmentPlayTrackChartBinding

class PlayTrackChartFragment : Fragment() {
    private lateinit var playTrackChartViewModel: PlayTrackChartViewModel
    private var _binding: FragmentPlayTrackChartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        playTrackChartViewModel = ViewModelProvider(this)[PlayTrackChartViewModel::class.java]
        _binding = FragmentPlayTrackChartBinding.inflate(inflater, container, false)
        binding.chartViewModel = playTrackChartViewModel
        binding.lifecycleOwner = this

        /*
        * Set up the recycler view
        * */
        val manager = LinearLayoutManager(this.activity)
        binding.recyclerView.layoutManager=manager
        val playTrackAdapter = PlayTrackAdapter()
        binding.recyclerView.adapter=playTrackAdapter

        /*
        * Observe changes of play track list and alsop submit the list to the view recycler
        * */
        playTrackChartViewModel.playTrackCharts.observe(viewLifecycleOwner, Observer {
            it?.let {
                playTrackAdapter.submitList(it)
            }
        })

        return binding.root
    }
}
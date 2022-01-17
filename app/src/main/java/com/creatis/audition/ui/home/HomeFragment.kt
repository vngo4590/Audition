package com.creatis.audition.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.creatis.audition.R
import com.creatis.audition.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*
        * Set up recycler view in horizontal layout
        * */
        val layout = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerFeaturedTrackCards.layoutManager = layout
        /*
        * Attaching Adapter
        * */
        val homeAdapter = HomeAdapter()
        binding.recyclerFeaturedTrackCards.adapter = homeAdapter

        homeViewModel.topPlayTracks.observe(viewLifecycleOwner, Observer {
            it?.let {
                homeAdapter.submitList(it.map{ track ->
                    TrackAndPropertiesAdapter(track)
                })
            }
        })
        binding.textSeeMoreTopTracks.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_navigation_home_to_navigation_play_track_chart)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
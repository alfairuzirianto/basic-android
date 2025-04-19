package com.example.dicodingevent.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.adapter.FinishedAdapter
import com.example.dicodingevent.data.response.ListEventsItem
import com.example.dicodingevent.databinding.FragmentHomeBinding
import com.example.dicodingevent.adapter.HomeUpcomingAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel by viewModels<HomeViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.allEvents.observe(viewLifecycleOwner) { allEvents ->
            setAllEvents(allEvents)
        }

        homeViewModel.upcomingEvents.observe(viewLifecycleOwner) { upcomingEvents ->
            setUpcomingData(upcomingEvents)
        }

        homeViewModel.finishedEvents.observe(viewLifecycleOwner) { finishedEvents ->
            setFinishedData(finishedEvents)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        val layoutManager1 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcoming.layoutManager = layoutManager1

        val layoutManager2 = LinearLayoutManager(context)
        binding.rvFinished.layoutManager = layoutManager2

        val layoutManager3 = LinearLayoutManager(context)
        binding.rvAll.layoutManager = layoutManager3

        return root
    }

    private fun setAllEvents(allEvents: List<ListEventsItem>){
        val adapter = FinishedAdapter()
        adapter.submitList(allEvents)
        binding.rvAll.adapter = adapter
        if (allEvents.isEmpty()) {
            binding.textNullAll.visibility = View.VISIBLE
        }
    }
    private fun setFinishedData(finishedEvents: List<ListEventsItem>){
        val adapter = FinishedAdapter()
        adapter.submitList(finishedEvents)
        binding.rvFinished.adapter = adapter
        if (finishedEvents.isEmpty()) {
            binding.textNullFinished.visibility = View.VISIBLE
        }
    }

    private fun setUpcomingData(upcomingEvents: List<ListEventsItem>) {
        val adapter = HomeUpcomingAdapter()
        adapter.submitList(upcomingEvents)
        binding.rvUpcoming.adapter = adapter
        if (upcomingEvents.isEmpty()) {
            binding.textNullUpcoming.visibility = View.VISIBLE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.progressBar1.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.dicodingevent.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.adapter.UpcomingAdapter
import com.example.dicodingevent.data.response.EventData
import com.example.dicodingevent.databinding.FragmentUpcomingBinding

class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val upcomingViewModel by viewModels<UpcomingViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        upcomingViewModel.upcomingEvents.observe(viewLifecycleOwner) { upcomingEvents ->
            setUpcomingData(upcomingEvents)
        }

        upcomingViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(context)
        binding.rvUpcoming.layoutManager = layoutManager

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    upcomingViewModel.searchEvents(searchView.text.toString())
                    false
                }
        }

        return root
    }

    private fun setUpcomingData(upcomingEvents: List<EventData>) {
        val adapter = UpcomingAdapter()
        adapter.submitList(upcomingEvents)
        binding.rvUpcoming.adapter = adapter
        if (upcomingEvents.isEmpty()) {
            binding.textNull.visibility = View.VISIBLE
        } else {
            binding.textNull.visibility = View.GONE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
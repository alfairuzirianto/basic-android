package com.example.dicodingevent.ui.finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingevent.adapter.FinishedAdapter
import com.example.dicodingevent.data.response.EventData
import com.example.dicodingevent.databinding.FragmentFinishedBinding

class FinishedFragment : Fragment() {

    private var _binding: FragmentFinishedBinding? = null
    private val finishedViewModel by viewModels<FinishedViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        finishedViewModel.finishedEvents.observe(viewLifecycleOwner) { finishedEvents ->
            setFinishedData(finishedEvents)
        }

        finishedViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(context)
        binding.rvFinished.layoutManager = layoutManager

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    finishedViewModel.searchEvents(searchView.text.toString())
                    false
                }
        }

        return root
    }

    private fun setFinishedData(finishedEvents: List<EventData>) {
        val adapter = FinishedAdapter()
        adapter.submitList(finishedEvents)
        binding.rvFinished.adapter = adapter
        if (finishedEvents.isEmpty()) {
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
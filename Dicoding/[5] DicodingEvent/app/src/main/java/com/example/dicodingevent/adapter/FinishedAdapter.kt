package com.example.dicodingevent.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.response.EventData
import com.example.dicodingevent.databinding.ItemRectangleBinding
import com.example.dicodingevent.ui.detail.DetailActivity

class FinishedAdapter : ListAdapter<EventData, FinishedAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRectangleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    class ViewHolder(private val binding: ItemRectangleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventData) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(event.imageLogo)
                    .into(imgEvent)
                textEvent.text = event.name
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("ACTIVE", false)
                    intent.putExtra("EVENT_ID", event.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EventData>() {
            override fun areItemsTheSame(
                oldItem: EventData,
                newItem: EventData
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: EventData,
                newItem: EventData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}
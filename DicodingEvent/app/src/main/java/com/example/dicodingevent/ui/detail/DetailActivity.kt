package com.example.dicodingevent.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.dicodingevent.data.response.EventData
import com.example.dicodingevent.databinding.ActivityDetailBinding
import androidx.core.net.toUri

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var extraEventID: Int = -1
    private var extraActive: Boolean = false
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        extraEventID = intent.getIntExtra("EVENT_ID", -1)
        extraActive = intent.getBooleanExtra("ACTIVE", false)
        viewModel.findEvent(extraEventID)

        viewModel.event.observe(this) { event ->
            setDetailEvent(event)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailEvent(event: EventData) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(event.mediaCover)
                .into(ivBanner)
            textTitle.text = event.name
            textOwner.text = "oleh ${event.ownerName}"
            textTime.text = event.beginTime
            textQuota.text = "${event.quota - event.registrants} (quota left)"
            textDesc.text = HtmlCompat.fromHtml(
                event.description,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            btnRegister.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, event.link.toUri()))
            }
            if (!extraActive) {
                btnRegister.text = "Event ini telah berakhir"
                btnRegister.isEnabled = false
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
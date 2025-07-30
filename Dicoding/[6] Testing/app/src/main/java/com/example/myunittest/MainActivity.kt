package com.example.myunittest

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myunittest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = MainViewModel(CuboidModel())

        binding.btnSave.setOnClickListener(this)
        binding.btnCalculateVolume.setOnClickListener(this)
        binding.btnCalculateSurfaceArea.setOnClickListener(this)
        binding.btnCalculateCircumference.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val w = binding.edtWidth.text.toString().trim()
        val l = binding.edtLength.text.toString().trim()
        val h = binding.edtHeight.text.toString().trim()

        when {
            TextUtils.isEmpty(w) -> binding.edtWidth.error = "Field ini tidak boleh kosong"
            TextUtils.isEmpty(l) -> binding.edtLength.error = "Field ini tidak boleh kosong"
            TextUtils.isEmpty(h) -> binding.edtHeight.error = "Field ini tidak boleh kosong"
            else -> {
                val width = w.toDouble()
                val length = l.toDouble()
                val height = h.toDouble()
                when (v?.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(width, length, height)
                        visible()
                    }
                    R.id.btn_calculate_volume -> {
                        binding.tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        binding.tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calculate_circumference -> {
                        binding.tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        binding.btnSave.visibility = View.GONE
        binding.btnCalculateSurfaceArea.visibility = View.VISIBLE
        binding.btnCalculateVolume.visibility = View.VISIBLE
        binding.btnCalculateCircumference.visibility = View.VISIBLE
    }

    private fun gone() {
        binding.btnSave.visibility = View.VISIBLE
        binding.btnCalculateSurfaceArea.visibility = View.GONE
        binding.btnCalculateVolume.visibility = View.GONE
        binding.btnCalculateCircumference.visibility = View.GONE
    }
}
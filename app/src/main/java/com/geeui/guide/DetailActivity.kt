package com.geeui.guide

import android.os.Bundle
import com.geeui.guide.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.included.back.setOnClickListener { finish() }
        val language = intent.getStringExtra("language")
        when (language) {
            "zh" -> {
                binding.img.setImageResource(R.drawable.zh)
                binding.desc.setText("扫码查看")
            }
            "en" -> {
                binding.img.setImageResource(R.drawable.en)
                binding.desc.setText("Scan the code")
            }
            else -> {
                binding.img.setImageResource(R.drawable.en)
                binding.desc.setText("Scan the code")
            }
        }

    }
}
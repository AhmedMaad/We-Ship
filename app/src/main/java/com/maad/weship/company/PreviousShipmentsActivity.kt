package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityPreviousShipmentsBinding

class PreviousShipmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPreviousShipmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
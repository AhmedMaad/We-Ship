package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityCurrentShipmentsBinding

class CurrentShipmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCurrentShipmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
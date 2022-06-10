package com.maad.weship.shipping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityShippingCompanyBinding

class ShippingCompanyHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShippingCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
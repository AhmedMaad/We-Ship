package com.maad.weship.shipping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityShippingSignUp2Binding
import com.maad.weship.databinding.ActivityShippingSignUp3Binding

class ShippingSignUp3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShippingSignUp3Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
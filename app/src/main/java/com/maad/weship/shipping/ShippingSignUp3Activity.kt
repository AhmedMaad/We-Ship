package com.maad.weship.shipping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.ParentActivity
import com.maad.weship.databinding.ActivityShippingSignUp2Binding
import com.maad.weship.databinding.ActivityShippingSignUp3Binding

class ShippingSignUp3Activity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShippingSignUp3Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
package com.maad.weship.shipping

import android.os.Bundle
import com.maad.weship.ParentActivity
import com.maad.weship.databinding.ActivityShippingSignUpBinding

class ShippingSignUpActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShippingSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
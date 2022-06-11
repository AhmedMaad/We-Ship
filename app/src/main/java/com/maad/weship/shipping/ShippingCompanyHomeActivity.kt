package com.maad.weship.shipping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityShippingCompanyHomeBinding
import com.maad.weship.general.ParentActivity

class ShippingCompanyHomeActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShippingCompanyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
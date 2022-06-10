package com.maad.weship.shipping

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.maad.weship.databinding.ActivityShippingSignUp2Binding

class ShippingSignUp2Activity : AppCompatActivity() {

    /*private val types = arrayOf("x", "y", "z", "h")*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShippingSignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.companyTypeSpinner.adapter = adapter*/



    }

}
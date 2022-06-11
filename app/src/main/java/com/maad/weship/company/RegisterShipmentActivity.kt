package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityRegisterShipmentBinding

class RegisterShipmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterShipmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
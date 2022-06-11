package com.maad.weship.company

import android.content.Intent
import android.os.Bundle
import com.maad.weship.databinding.ActivityCompanyHomeBinding
import com.maad.weship.general.ParentActivity

class CompanyHomeActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompanyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerShipmentBtn.setOnClickListener {
            startActivity(Intent(this, RegisterShipmentActivity::class.java))
        }

        binding.viewCurrentShipmentBtn.setOnClickListener {
            startActivity(Intent(this, CurrentShipmentsActivity::class.java))
        }

        binding.viewPreviousShipmentsBtn.setOnClickListener {
            startActivity(Intent(this, PreviousShipmentsActivity::class.java))
        }

    }

}
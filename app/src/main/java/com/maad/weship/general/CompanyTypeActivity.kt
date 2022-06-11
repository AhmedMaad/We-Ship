package com.maad.weship.general

import android.content.Intent
import android.os.Bundle
import com.maad.weship.company.CompanySignUpActivity
import com.maad.weship.databinding.ActivityCompanyTypeBinding
import com.maad.weship.shipping.ShippingSignUpActivity

class CompanyTypeActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompanyTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shippingCv.setOnClickListener {
            startActivity(Intent(this, ShippingSignUpActivity::class.java))
        }

        binding.companyCv.setOnClickListener {
            startActivity(Intent(this, CompanySignUpActivity::class.java))
        }

    }

}
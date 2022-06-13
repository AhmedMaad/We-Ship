package com.maad.weship.general

import android.content.Intent
import android.os.Bundle
import com.maad.weship.databinding.ActivityCompanyTypeBinding

class CompanyTypeActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompanyTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.shippingCv.setOnClickListener { openSignup("shipping") }
        binding.companyCv.setOnClickListener { openSignup("other") }
    }

    private fun openSignup(type: String) {
        val i = Intent(this, SignUpActivity::class.java)
        i.putExtra("type", type)
        startActivity(i)
    }

}
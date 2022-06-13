package com.maad.weship.general

import android.content.Intent
import android.os.Bundle
import com.maad.weship.databinding.ActivitySignUp2Binding

class SignUp2Activity : ParentActivity() {

    /*private val types = arrayOf("x", "y", "z", "h")*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.companyTypeSpinner.adapter = adapter*/

        val shipping = intent.getParcelableExtra<Company>("shipping")!!

        binding.nextBtn.setOnClickListener {
            shipping.companyName = binding.nameEt.text.toString()
            shipping.taxNumber = binding.taxNumberEt.text.toString()
            shipping.commercialRegistration = binding.commercialRegistrationEt.text.toString()
            shipping.companyAddress = binding.addressEt.text.toString()
            shipping.companyPhoneNumber = binding.phoneNumberEt.text.toString()
            shipping.companyType = binding.companyTypeEt.text.toString()

            val i = Intent(this, SignUp3Activity::class.java)
            i.putExtra("shipping", shipping)
            startActivity(i)
        }

    }

}
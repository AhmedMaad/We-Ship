package com.maad.weship.shipping

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maad.weship.ParentActivity
import com.maad.weship.databinding.ActivityShippingSignUpBinding

class ShippingSignUpActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShippingSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val username = binding.usernameEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (email.isEmpty() || username.isEmpty() || password.isEmpty())
                Toast.makeText(this, "Missing Required Field/s", Toast.LENGTH_SHORT).show()
            else {
                val i = Intent(this, ShippingSignUp2Activity::class.java)
                val shipping = ShippingCompany(email, username, password)
                i.putExtra("shipping", shipping)
                startActivity(i)
            }

        }

    }

}
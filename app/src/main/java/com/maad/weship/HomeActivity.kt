package com.maad.weship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityHomeBinding
import com.maad.weship.shipping.ShippingSignUpActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInBtn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this, CompanyTypeActivity::class.java))
        }

    }

}
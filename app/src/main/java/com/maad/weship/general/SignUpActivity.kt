package com.maad.weship.general

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.maad.weship.databinding.ActivitySignUpBinding

class SignUpActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val username = binding.usernameEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (email.isEmpty() || username.isEmpty() || password.isEmpty())
                Toast.makeText(this, "Missing Required Field/s", Toast.LENGTH_SHORT).show()
            else {
                val i = Intent(this, SignUp2Activity::class.java)
                val shipping = Company(email, username, password)
                shipping.userType = intent.getStringExtra("type")!!
                i.putExtra("shipping", shipping)
                startActivity(i)
            }

        }

    }

}
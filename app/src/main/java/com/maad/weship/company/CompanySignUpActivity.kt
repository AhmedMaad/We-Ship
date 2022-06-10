package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityCompanySignUpBinding
import com.maad.weship.databinding.ActivityCompanyTypeBinding

class CompanySignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompanySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
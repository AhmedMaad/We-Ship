package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityCompanySignUpBinding
import com.maad.weship.databinding.ActivityHomeBinding

class CompanyHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
package com.maad.weship.company

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cashCv.setOnClickListener {
            //TODO: Add request to history and delete from current recycler view
        }

        binding.visaCv.setOnClickListener {
            startActivity(Intent(this, VisaActivity::class.java))
        }

    }

}
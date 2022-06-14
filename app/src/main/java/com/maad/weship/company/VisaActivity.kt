package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.maad.weship.R

class VisaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visa)

        val visaPay: Button = findViewById(R.id.pay_btn)
        visaPay.setOnClickListener {
            //TODO: Add request to history and delete from current recycler view
        }

    }
}
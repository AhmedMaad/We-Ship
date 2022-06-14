package com.maad.weship.company

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maad.weship.databinding.ActivityPreviousShipmentsBinding
import com.maad.weship.general.ParentActivity

class PreviousShipmentsActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPreviousShipmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //show toast in case of no previous history, hide progress bar

    }

}
package com.maad.weship.company

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.maad.weship.databinding.ActivityCompanyHomeBinding
import com.maad.weship.general.ParentActivity
import com.maad.weship.general.ProfileActivity


class CompanyHomeActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCompanyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerShipmentBtn.setOnClickListener {
            startActivity(Intent(this, RegisterShipmentActivity::class.java))
        }

        binding.viewCurrentShipmentBtn.setOnClickListener {
            startActivity(Intent(this, CurrentShipmentsActivity::class.java))
        }

        binding.viewPreviousShipmentsBtn.setOnClickListener {
            startActivity(Intent(this, PreviousShipmentsActivity::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val item = menu!!.add("Profile")
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER)
        item.setOnMenuItemClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            return@setOnMenuItemClickListener true
        }
        return super.onCreateOptionsMenu(menu)
    }

}
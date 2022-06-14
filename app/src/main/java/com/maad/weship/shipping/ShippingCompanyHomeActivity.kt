package com.maad.weship.shipping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.maad.weship.databinding.ActivityShippingCompanyHomeBinding
import com.maad.weship.general.ParentActivity
import com.maad.weship.general.ProfileActivity

class ShippingCompanyHomeActivity : ParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShippingCompanyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        * read all requests with request type "pending"
        * the requests should not exist in the "requestshippingstatus" collection before
        * */

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
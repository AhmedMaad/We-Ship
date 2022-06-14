package com.maad.weship.shipping

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maad.weship.R
import com.maad.weship.company.Shipment
import com.maad.weship.databinding.ActivityShippingCompanyHomeBinding
import com.maad.weship.general.ParentActivity
import com.maad.weship.general.ProfileActivity
import com.ramotion.foldingcell.FoldingCell


class ShippingCompanyHomeActivity : ParentActivity(), RequestsAdapter.ItemClickListener {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityShippingCompanyHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShippingCompanyHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Firebase.firestore

        /*
        * read all requests with request type "pending"
        * the requests should not exist in the "requestshippingstatus" collection before
        * */

        db.collection("shipments").get().addOnSuccessListener {
            val all = it.toObjects(Shipment::class.java)

            val divider =
                DividerItemDecoration(binding.requestsRv.context, DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.line_divider)!!)
            binding.requestsRv.addItemDecoration(divider)
            val adapter = RequestsAdapter(this, all as ArrayList<Shipment>, this)
            binding.requestsRv.adapter = adapter
            binding.progress.visibility = View.INVISIBLE


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

    override fun onItemClick(position: Int, cell: FoldingCell) {
        cell.toggle(false)
    }

    override fun onAcceptBtnClick(position: Int) {
        Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show();
    }

}
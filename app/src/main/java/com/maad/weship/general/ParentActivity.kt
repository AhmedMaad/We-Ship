package com.maad.weship.general

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.maad.weship.R

open class ParentActivity: AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.general_menu, menu)
        return true
    }

}
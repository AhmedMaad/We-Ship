package com.maad.weship

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

open class ParentActivity: AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.general_menu, menu)
        return true
    }

}
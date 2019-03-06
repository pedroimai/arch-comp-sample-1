package com.pedroimai.archcomp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pedroimai.archcomp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contact_container, ContactFragment())
                .add(R.id.contact_list_container, ContactListFragment()).commit()

        }
    }
}

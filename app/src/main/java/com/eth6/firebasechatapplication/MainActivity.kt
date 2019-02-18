package com.eth6.firebasechatapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_people -> {
                    true
                }
                R.id.navigation_account -> {
                    startActivity(Intent(this,SignInActivity::class.java))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}

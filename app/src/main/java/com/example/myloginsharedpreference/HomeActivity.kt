package com.example.myloginsharedpreference

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myloginsharedpreference.MainActivity.Companion.PREFS_NAME
import com.example.myloginsharedpreference.MainActivity.Companion.USERNAME

class HomeActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        username = sharedPreferences.getString(USERNAME, null)

        val teks = findViewById<TextView>(R.id.welcome)
        teks.text = "Welcome $username"

        val button = findViewById<Button>(R.id.btn_logout)

        button.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
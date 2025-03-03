package com.example.myloginsharedpreference

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    companion object{
        const val PREFS_NAME = "loginPrefs"
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<TextInputEditText>(R.id.et_user)
        val pass = findViewById<TextInputEditText>(R.id.et_pass)
        val login = findViewById<Button>(R.id.btn_login)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        username = sharedPreferences.getString(USERNAME, null)
        password = sharedPreferences.getString(PASSWORD, null)

        login.setOnClickListener {
            if (user.text?.isEmpty() == true && pass.text?.isEmpty() == true) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            }else {
                val editor = sharedPreferences.edit()
                editor.putString(USERNAME, user.text.toString())
                editor.putString(PASSWORD, pass.text.toString())
                editor.apply()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (username != null && password != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
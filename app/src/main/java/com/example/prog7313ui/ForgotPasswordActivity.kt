package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.repository.UserRepository
import com.example.prog7313ui.viewmodel.AuthViewModel
import com.example.prog7313ui.viewmodel.AuthViewModelFactory
import kotlin.jvm.java
import kotlin.text.isEmpty
import kotlin.text.trim

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var newPasswordInput: EditText
    private lateinit var resetButton: Button
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // View setup
        usernameInput = findViewById(R.id.forgotUsernameInput)
        newPasswordInput = findViewById(R.id.newPasswordInput)
        resetButton = findViewById(R.id.resetPasswordButton)

//(Tutorialspoint, 2024)

        // ViewModel setup
        val userDao = AppDatabase.getInstance(applicationContext).userDao()
        val repository = UserRepository(userDao)
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository))[AuthViewModel::class.java]

        // Back to Login
        val backToLoginBtn = findViewById<ImageButton>(R.id.backToLoginBtn)
        backToLoginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//(Tuto, 2023)

        // Reset button logic
        resetButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val newPassword = newPasswordInput.text.toString().trim()

            if (username.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.resetPassword(username, newPassword) { success ->
                if (success) {
                    Toast.makeText(this, "Password updated!", Toast.LENGTH_SHORT).show()
                    // Redirect to login screen
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Username not found!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ForgotPasswordActivity::class.java))
                    finish()
                }
            }
        }
    }
}

//(Sekhon, 2020)


package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.repository.UserRepository
import com.example.prog7313ui.viewmodel.AuthViewModel
import com.example.prog7313ui.viewmodel.AuthViewModelFactory
import com.example.prog7313ui.viewmodel.LoginResult

class MainActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var forgotPassword: Button
    private lateinit var signInButton: Button
    private lateinit var logInButton: Button
    private lateinit var continueBtn: Button
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI
        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        forgotPassword = findViewById(R.id.forgotPassword)
        signInButton = findViewById(R.id.signInButton)
        logInButton = findViewById(R.id.logInButton)
        continueBtn = findViewById(R.id.continueBtn)

        // Set up ViewModel
        val userDao = AppDatabase.getDatabase(applicationContext).userDao()
        val repository = UserRepository(userDao)
        viewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(repository)
        )[AuthViewModel::class.java]

        // Observe login/register result
        viewModel.loginResult.observe(this) { result ->
            when (result) {
                is LoginResult.Success -> {
                    startActivity(Intent(this, HubActivity::class.java))
                    finish()
                }
                is LoginResult.UserNotFound -> {
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                }
                is LoginResult.WrongPassword -> {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Sign-in logic
        signInButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.register(username, password)
        }

        // Login logic
        logInButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username and password required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(username, password)
        }

        // Continue without login
        continueBtn.setOnClickListener {
            startActivity(Intent(this, HubActivity::class.java))
            finish()
        }

        // Forgot Password logic
        forgotPassword.setOnClickListener{
            val username = usernameInput.text.toString().trim()

            if (username.isEmpty() ) {
                Toast.makeText(this, "Valid username required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            finish()
        }
    }
}

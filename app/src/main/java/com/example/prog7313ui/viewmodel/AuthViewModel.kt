package com.example.prog7313ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prog7313ui.data.dao.UserDao
import com.example.prog7313ui.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    private val _authState = MutableLiveData<Boolean>()
    val authState: LiveData<Boolean> = _authState

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _passwordReset = MutableLiveData<Boolean>()
    val passwordReset: LiveData<Boolean> = _passwordReset



    fun register(username: String,password: String) {
        viewModelScope.launch {
            val success = repository.registerUser(username,password)
            _authState.postValue(success)
        }
    }


    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = repository.loginUser(username, password)
            _loginResult.postValue(result)
        }
    }

    fun resetPassword(username: String, newPassword: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = repository.resetPassword(username, newPassword)
            onComplete(result)
        }
    }

}

//(Sekhon, 2020)
//(Tuto, 2023)
//(Tutorialspoint, 2024)

package com.example.prog7313ui.viewmodel

sealed class LoginResult {
    object Success : LoginResult()
    object UserNotFound : LoginResult()
    object WrongPassword : LoginResult()
}

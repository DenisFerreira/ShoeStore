package com.udacity.shoestore.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    //Event - Logged
    private val _eventLogged = MutableLiveData<Boolean>()
    val eventLogged: LiveData<Boolean>
        get() = _eventLogged

    fun login() {
        _eventLogged.value = validateUserPassword(username, password)
    }

    fun logout() {
        _eventLogged.value = false
    }

    fun createNewAcount() {
        _eventLogged.value = true
    }

    private fun validateUserPassword(
        _username: MutableLiveData<String>,
        _password: MutableLiveData<String>
    ): Boolean? {
        return true
    }
}
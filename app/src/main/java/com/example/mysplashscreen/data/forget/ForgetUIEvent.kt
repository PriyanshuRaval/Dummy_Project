package com.example.mysplashscreen.data.forget

import com.example.mysplashscreen.data.login.LoginUIEvent

sealed class ForgetUIEvent {
    data class EmailChanged(val email:String): ForgetUIEvent()

    object ForgetButtonClicked : ForgetUIEvent()
}
package com.example.mysplashscreen.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    object SignUpScreen : Screen()
    object TermsAndConditionScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
    object ForgetPasswordScreen : Screen()
}

object PostOfficeAppRouter {

    val currentScreen : MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigate_to(destination : Screen){
        currentScreen.value = destination
    }
}
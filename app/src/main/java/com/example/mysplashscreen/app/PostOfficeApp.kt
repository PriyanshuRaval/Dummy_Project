package com.example.mysplashscreen.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mysplashscreen.Screens.ForgetPasswordScreen
import com.example.mysplashscreen.Screens.HomeScreen
import com.example.mysplashscreen.Screens.LoginScreen
import com.example.mysplashscreen.Screens.SignUpScreen
import com.example.mysplashscreen.Screens.TermsAndConditionScreen
import com.example.mysplashscreen.navigation.PostOfficeAppRouter
import com.example.mysplashscreen.navigation.Screen

@Composable
fun PostOfficeApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        
        Crossfade(targetState = PostOfficeAppRouter.currentScreen, label = "") {
            currentState ->
            when(currentState.value){
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
                is Screen.TermsAndConditionScreen -> {
                    TermsAndConditionScreen()
                }
                is Screen.LoginScreen -> {
                    LoginScreen()
                }
                is Screen.HomeScreen -> {
                    HomeScreen()
                }
                is Screen.ForgetPasswordScreen -> {
                    ForgetPasswordScreen()
                }
            }
            
        }
    }
}
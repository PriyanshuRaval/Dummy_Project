package com.example.mysplashscreen.data.forget

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mysplashscreen.data.login.LoginUIEvent
import com.example.mysplashscreen.data.login.LoginUIState
import com.example.mysplashscreen.data.login.LoginViewModel
import com.example.mysplashscreen.navigation.PostOfficeAppRouter
import com.example.mysplashscreen.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class ForgetViewModel : ViewModel() {

    private val TAG = ForgetViewModel::class.simpleName

    var forgetUIState = mutableStateOf(ForgetUIState())

    var forgetInProgress = mutableStateOf(false)


    fun onEvent(event : ForgetUIEvent) {
        when (event) {
            is ForgetUIEvent.EmailChanged -> {
                forgetUIState.value = forgetUIState.value.copy(
                    email = event.email
                )
            }
            is ForgetUIEvent.ForgetButtonClicked -> {
                forgetPassword()
            }
        }
    }

    private fun forgetPassword() {

        forgetInProgress.value = true

        val email = forgetUIState.value.email

        FirebaseAuth.getInstance()
            .sendPasswordResetEmail(email)
            .addOnCompleteListener {
                forgetInProgress.value = false
                Log.d(TAG,"Inside_forgetPassword_success")
                Log.d(TAG,"Is Successful = ${it.isSuccessful}")

                if(it.isSuccessful){
                    forgetInProgress.value = false
                    PostOfficeAppRouter.navigate_to(Screen.LoginScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside_forgetPassword_Failure")
                Log.d(TAG,"${it.localizedMessage}")

            }
    }


}
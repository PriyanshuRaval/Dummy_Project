package com.example.mysplashscreen.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mysplashscreen.data.rules.Validator
import com.example.mysplashscreen.data.signup.SignupUIEvent
import com.example.mysplashscreen.data.signup.SignupViewModel
import com.example.mysplashscreen.navigation.PostOfficeAppRouter
import com.example.mysplashscreen.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPasses = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event : LoginUIEvent){
        when(event){
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }
            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun login() {

        loginInProgress.value = true

        val email = loginUIState.value.email
        val password = loginUIState.value.password
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                loginInProgress.value = false
                Log.d(TAG,"Inside_login_success")
                Log.d(TAG,"Is Successful = ${it.isSuccessful}")

                if(it.isSuccessful){
                    loginInProgress.value = false
                    PostOfficeAppRouter.navigate_to(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside_login_Failure")
                Log.d(TAG,"${it.localizedMessage}")

            }
    }

    private fun validateLoginUIDataWithRules(){
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPasses.value = emailResult.status && passwordResult.status
    }

}
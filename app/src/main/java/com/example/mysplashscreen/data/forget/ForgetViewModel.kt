package com.example.mysplashscreen.data.forget

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysplashscreen.data.login.LoginUIEvent
import com.example.mysplashscreen.data.login.LoginUIState
import com.example.mysplashscreen.data.login.LoginViewModel
import com.example.mysplashscreen.data.rules.Validator
import com.example.mysplashscreen.navigation.PostOfficeAppRouter
import com.example.mysplashscreen.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class ForgetViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = ForgetViewModel::class.simpleName

    var forgetUIState = mutableStateOf(ForgetUIState())

    var allValidationsPasses = mutableStateOf(false)

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

            else -> {

            }
        }
        validateEmailWithRules()
    }

    private fun forgetPassword() {
        forgetInProgress.value = true

        val email = forgetUIState.value.email
        Log.d("Email text","$email")


        // Check if a user with the provided email exists
        FirebaseAuth.getInstance()
            .fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    Log.d("signInMethods","${signInMethods}")
                    if (!signInMethods.isNullOrEmpty()) {
                        // User with the email exists, send the password reset email
                        FirebaseAuth.getInstance()
                            .sendPasswordResetEmail(email)
                            .addOnCompleteListener { resetTask ->
                                forgetInProgress.value = false
                                if (resetTask.isSuccessful) {
                                    // Password reset email sent successfully
                                    PostOfficeAppRouter.navigate_to(Screen.LoginScreen)
                                    Toast.makeText(
                                        getApplication(),
                                        "Password Link Sent Successful",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    // Handle the password reset email failure
                                    Toast.makeText(
                                        getApplication(),
                                        "Failed to send password reset email",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        // User with the email does not exist
                        forgetInProgress.value = false
                        Toast.makeText(
                            getApplication(),
                            "No user with this email address exists",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // Handle the fetchSignInMethodsForEmail failure
                    forgetInProgress.value = false
                    Toast.makeText(
                        getApplication(),
                        "Failed to check email existence",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    private fun validateEmailWithRules() {
        val emailResult = Validator.validateEmail(
            email = forgetUIState.value.email
        )

        forgetUIState.value = forgetUIState.value.copy(
            emailError = emailResult.status
        )

        allValidationsPasses.value = emailResult.status

    }

}
package com.example.mysplashscreen.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mysplashscreen.data.rules.Validator
import com.example.mysplashscreen.navigation.PostOfficeAppRouter
import com.example.mysplashscreen.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignupViewModel : ViewModel() {

    private val TAG = SignupViewModel::class.simpleName

    var signupUIState = mutableStateOf(SignupUIState())

    var allValidationsPasses = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent){
        when(event){
            is SignupUIEvent.FirstNameChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }
            is SignupUIEvent.LastNameChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }
            is SignupUIEvent.EmailChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignupUIEvent.PasswordChanged -> {
                signupUIState.value = signupUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }
            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                signupUIState.value = signupUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDatawithRules()


    }

    private fun signUp() {
        Log.d(TAG,"Inside_Sign up")
        printState()
        createUserInFirebase(
            email = signupUIState.value.email,
            password = signupUIState.value.password)
    }

    private fun validateDatawithRules() {
        val fNameResult = Validator.validateFirstName(fName = signupUIState.value.firstName)
        val lNameResult = Validator.validateLastName(lName = signupUIState.value.lastName)
        val emailResult = Validator.validateEmail(email = signupUIState.value.email)
        val passwordResult = Validator.validatePassword(password = signupUIState.value.password)
        val privacyPolicyResult = Validator.validatePrivacypolicyAcceptance(statusValue = signupUIState.value.privacyPolicyAccepted)

        Log.d(TAG,"Inside_validateDatawithRules")
        Log.d(TAG,"FirstName = $fNameResult")
        Log.d(TAG,"LastName = $lNameResult")
        Log.d(TAG,"Email = $emailResult")
        Log.d(TAG,"Password = $passwordResult")
        Log.d(TAG,"privacyPolicy = $privacyPolicyResult")

        signupUIState.value = signupUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        allValidationsPasses.value = fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status && privacyPolicyResult.status
    }

    private fun printState(){
        Log.d(TAG,"Inside_PrintState")
        Log.d(TAG,signupUIState.value.toString())

    }

    private fun createUserInFirebase(email : String,password : String){

        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG,"Inside_OnCompleteListener")
                Log.d(TAG,"is Successful = ${it.isSuccessful}")
                signUpInProgress.value = false

                if(it.isSuccessful){
                    PostOfficeAppRouter.navigate_to(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside_OnFailureListener")
                Log.d(TAG,"Exception = ${it.localizedMessage}")
            }
    }

    fun logout(){

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListner = AuthStateListener {
            if(it.currentUser == null){
                Log.d(TAG,"Inside signout Success")
                PostOfficeAppRouter.navigate_to(Screen.LoginScreen)
            } else {
                Log.d(TAG,"Inside signout Success is not complete")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListner)
    }
}
package com.example.mysplashscreen.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysplashscreen.R
import com.example.mysplashscreen.components.ButtonComponent
import com.example.mysplashscreen.components.CheckboxComponent
import com.example.mysplashscreen.components.ClickableLoginTextComponent
import com.example.mysplashscreen.components.DividerTextComponent
import com.example.mysplashscreen.components.HeadingTextComponent
import com.example.mysplashscreen.components.MyTextFieldComponent
import com.example.mysplashscreen.components.NormalTextComponent
import com.example.mysplashscreen.components.PasswordTextFieldComponent
import com.example.mysplashscreen.data.signup.SignupUIEvent
import com.example.mysplashscreen.data.signup.SignupViewModel
import com.example.mysplashscreen.navigation.PostOfficeAppRouter
import com.example.mysplashscreen.navigation.Screen

@Composable
fun SignUpScreen(signupViewModel: SignupViewModel = viewModel()){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Surface(color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()){
                NormalTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))

                Spacer(Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.first_name), painterResource = painterResource(
                    id = R.drawable.profile), onTextSelected = {
                    signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
                    errorStatus = signupViewModel.signupUIState.value.firstNameError
                )

                MyTextFieldComponent(labelValue = stringResource(id = R.string.last_name), painterResource = painterResource(
                    id = R.drawable.profile), onTextSelected = {
                    signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                },
                    errorStatus = signupViewModel.signupUIState.value.lastNameError
                )

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email), painterResource = painterResource(
                    id = R.drawable.message),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.signupUIState.value.emailError
                )

                PasswordTextFieldComponent(labelValue = stringResource(id = R.string.password), painterResource = painterResource(
                    id = R.drawable.ic_lock),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.signupUIState.value.passwordError
                )

                CheckboxComponent(
                    value = stringResource(id = R.string.terms_and_condition),
                    onTextSelected = {
                        PostOfficeAppRouter.navigate_to(Screen.TermsAndConditionScreen)
                    },
                    onCheckedChange = {
                        signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationsPasses.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true,onTextSelected = {
                    PostOfficeAppRouter.navigate_to(Screen.LoginScreen)
                })
            }
        }

        if(signupViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}
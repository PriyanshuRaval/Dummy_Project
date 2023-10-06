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
import com.example.mysplashscreen.components.ClickableLoginTextComponent
import com.example.mysplashscreen.components.ClickableUnderlineTextComponent
import com.example.mysplashscreen.components.DividerTextComponent
import com.example.mysplashscreen.components.HeadingTextComponent
import com.example.mysplashscreen.components.MyTextFieldComponent
import com.example.mysplashscreen.components.NormalTextComponent
import com.example.mysplashscreen.components.PasswordTextFieldComponent
import com.example.mysplashscreen.data.login.LoginUIEvent
import com.example.mysplashscreen.data.login.LoginViewModel
import com.example.mysplashscreen.navigation.PostOfficeAppRouter
import com.example.mysplashscreen.navigation.Screen
import com.example.mysplashscreen.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()){
                NormalTextComponent(value = stringResource(id = R.string.hello))

                HeadingTextComponent(value = stringResource(id = R.string.welcone))

                Spacer(Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email), painterResource = painterResource(
                    id = R.drawable.message), onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                    )

                PasswordTextFieldComponent(labelValue = stringResource(id = R.string.password), painterResource = painterResource(
                    id = R.drawable.ic_lock), onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                    false

                )
                Spacer(Modifier.height(40.dp))


                ClickableUnderlineTextComponent(
                    value = stringResource(id = R.string.forgot_password), onTextSelected = {
                    PostOfficeAppRouter.navigate_to(Screen.ForgetPasswordScreen)
                }
                )

                Spacer(Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPasses.value
                )

                Spacer(Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false,onTextSelected = {
                    PostOfficeAppRouter.navigate_to(Screen.SignUpScreen)
                })

            }
        }
        if(loginViewModel.loginInProgress.value){
            CircularProgressIndicator()
        }
    }



    SystemBackButtonHandler {
        PostOfficeAppRouter.navigate_to(Screen.SignUpScreen)
    }

}

@Preview
@Composable
fun DefaultPreviewOfLoginScreen() {
    LoginScreen()
}
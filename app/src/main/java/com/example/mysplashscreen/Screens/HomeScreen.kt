package com.example.mysplashscreen.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysplashscreen.R
import com.example.mysplashscreen.components.ButtonComponent
import com.example.mysplashscreen.components.HeadingTextComponent
import com.example.mysplashscreen.data.signup.SignupViewModel
import com.example.mysplashscreen.navigation.PostOfficeAppRouter
import com.example.mysplashscreen.navigation.Screen
import com.example.mysplashscreen.navigation.SystemBackButtonHandler

@Composable
fun HomeScreen(signupViewModel: SignupViewModel = viewModel()){
    Surface(color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            
            HeadingTextComponent(value = stringResource(R.string.home))
            
            ButtonComponent(
                value = stringResource(R.string.logout),
                onButtonClicked = {
                signupViewModel.logout()
                },
                isEnabled = true
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(SignupViewModel())
}
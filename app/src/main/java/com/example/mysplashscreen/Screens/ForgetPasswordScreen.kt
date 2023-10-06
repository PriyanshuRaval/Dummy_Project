package com.example.mysplashscreen.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.mysplashscreen.components.HeadingTextComponent
import com.example.mysplashscreen.components.MyTextFieldComponent
import com.example.mysplashscreen.components.NormalTextComponent
import com.example.mysplashscreen.data.forget.ForgetUIEvent
import com.example.mysplashscreen.data.forget.ForgetViewModel
import com.example.mysplashscreen.data.login.LoginUIEvent

@Composable
fun ForgetPasswordScreen(forgetViewModel: ForgetViewModel = viewModel()){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
                .align(Alignment.Center)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)){

                HeadingTextComponent(value = stringResource(id = R.string.forgot_password))
                NormalTextComponent(value = stringResource(R.string.Forget_text))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email), painterResource = painterResource(
                    id = R.drawable.message), onTextSelected = {
                    forgetViewModel.onEvent(ForgetUIEvent.EmailChanged(it))
                },
                    errorStatus = forgetViewModel.forgetUIState.value.emailError
                )
            }
        }
    }
}

@Preview
@Composable
fun ForgetPasswordScreenPreview(){
    ForgetPasswordScreen()
}
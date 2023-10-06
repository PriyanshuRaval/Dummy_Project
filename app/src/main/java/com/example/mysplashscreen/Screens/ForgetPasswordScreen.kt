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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mysplashscreen.R
import com.example.mysplashscreen.components.HeadingTextComponent
import com.example.mysplashscreen.data.forget.ForgetViewModel

@Composable
fun ForgetPasswordScreen(forgetViewModel: ForgetViewModel = viewModel()){

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(color = Color.Blue,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp),
            shape = RoundedCornerShape(50.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()){

                HeadingTextComponent(value = stringResource(id = R.string.forgot_password))


            }
        }
    }
}

@Preview
@Composable
fun ForgetPasswordScreenPreview(){
    ForgetPasswordScreen()
}
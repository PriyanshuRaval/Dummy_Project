package com.example.mysplashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInOutExpo
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mysplashscreen.ui.theme.MySplashScreenTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen()
                }

        }
    }
    @Composable
    private fun SplashScreen(){
        val alpha = remember {
            Animatable(0f)
        }
        LaunchedEffect(key1 = true) {
            alpha.animateTo(1f,
                animationSpec = tween(500, easing = EaseInOutExpo)
            )
            delay(1000)
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            finish()
        }
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFB093C6)), contentAlignment = Alignment.Center
        ){
            Image(modifier = Modifier.alpha(alpha.value),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null )
        }
    }
}


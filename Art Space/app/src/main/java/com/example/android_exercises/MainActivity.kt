package com.example.android_exercises

import android.graphics.Paint
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_exercises.ui.theme.Android_exercisesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_exercisesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtWork_App()
                }
            }
        }
    }
}

@Preview
@Composable
fun app_preview(){
    ArtWork_App()
}

@Composable
fun ArtWork_App(){

    var state by remember {
        mutableStateOf(0)
    }

    val paintings = when(state%3) {
        0 -> R.drawable.vangogh
        1 -> R.drawable.michelangelo
        else -> R.drawable.leonardo_da_vinci
    }

    val description = when(state%3) {
        0 -> R.string.vangogh
        1 -> R.string.michelangelo
        else -> R.string.leonardo
    }

    ShowArt(image = paintings,
        description = description
    )
}

@Composable
fun ShowArt(image: Int, description: Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = image), contentDescription = stringResource(id = description))
    }
}
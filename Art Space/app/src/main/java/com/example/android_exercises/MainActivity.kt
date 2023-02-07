package com.example.android_exercises

import android.graphics.Paint
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
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
import androidx.compose.ui.unit.dp
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

    val name = when(state%3) {
        0 -> R.string.night
        1 -> R.string.adam
        else -> R.string.vitruvian
    }

    Column() {
        ShowArt(image = paintings,
            description = description
        )

        Spacer(modifier = Modifier.padding(top = 10.dp))

        ShowDescription(
            description = description,
            name = name
        )
        
        Spacer(modifier = Modifier.padding(top = 10.dp))
        
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { state -= 1 }) {
                Text(text = stringResource(id = R.string.previous))
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Button(onClick = { state += 1 }) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun ShowArt(image: Int, description: Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)) {
        Image(painter = painterResource(id = image), contentDescription = stringResource(id = description))
    }
}

@Composable
fun ShowDescription(description: Int, name: Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = name))
        Text(text = stringResource(id = description))
    }

}
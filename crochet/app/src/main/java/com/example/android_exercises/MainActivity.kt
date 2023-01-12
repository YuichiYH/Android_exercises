package com.example.android_exercises

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_exercises.ui.theme.Android_exercisesTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

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
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

    var width by remember {
        mutableStateOf("0")
    }

    var height by remember {
        mutableStateOf("0")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        // Titulo
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            //fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Amostra
        SamplePick(
            width,
            {width = it},
            height,
            {height = it}
        )

        //Esfera
        Row() {
            Image(
                painter = painterResource(id = R.drawable.crochet_ball2),
                contentDescription = stringResource(id = R.string.ball_description),

            )

        }
    }
}

@Composable
fun SamplePick(
    width: String,
    onWidthValueChange: (String) -> Unit,
    height: String,
    onHeightValueChange: (String) -> Unit
){
    Column() {
        Text(
            text = stringResource(id = R.string.sample),
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )



        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = width)
                Image(
                    painter = painterResource(id = R.drawable.rect),
                    contentDescription = stringResource(id = R.string.rect_description),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
            Text(text = height)
        }
        TextField(
            modifier = Modifier.padding(top = 20.dp),
            label = { stringResource(id = R.string.width)},
            value = width,
            onValueChange = onWidthValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier.padding(top = 10.dp),
            label = { stringResource(id = R.string.height)},
            value = height,
            onValueChange = onHeightValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

    }
}

@Preview
@Composable
fun DefaultPreview() {
    Android_exercisesTheme{
        App()
    }
}
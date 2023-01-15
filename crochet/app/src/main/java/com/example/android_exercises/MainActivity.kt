package com.example.android_exercises

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_exercises.ui.theme.Android_exercisesTheme
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import kotlin.math.PI
import kotlin.math.roundToInt

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

    var widthSet by remember {
        mutableStateOf("0")
    }
    val width = widthSet.toIntOrNull() ?: 0

    var heightSet by remember {
        mutableStateOf("0")
    }
    val height = heightSet.toIntOrNull() ?: 0

    var radiusSet by remember {
        mutableStateOf("0")
    }
    val radius = radiusSet.toIntOrNull() ?: 0

    val darkMode: Boolean = isSystemInDarkTheme()

    // points per cm squared
    val scale: Int = (width * height) / 16

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
        /* TODO: Find a way to fix crash of
            putting space or - in the keyboard
            creates invalid character  */
        SamplePick(
            width = widthSet,
            onWidthValueChange = {
                    value -> widthSet = value.filter{it.isDigit()}
                                 },
            height = heightSet,
            onHeightValueChange = {
                    value -> heightSet = value.filter{it.isDigit()}
                                  },
            radius = radiusSet,
            onRadiusValueChange = {
                    value -> radiusSet = value.filter{it.isDigit()}
                                  },
            darkMode = darkMode
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Esfera
        SphereCalc(
            scale = scale,
            radius = radius,
            darkMode = darkMode
        )
    }
}

@Composable
fun SamplePick(
    width: String,
    onWidthValueChange: (String) -> Unit,
    height: String,
    onHeightValueChange: (String) -> Unit,
    radius: String, onRadiusValueChange: (String) -> Unit,
    darkMode: Boolean
){
    val rect = when(darkMode){
        true -> painterResource(id = R.drawable.rect_darkmode)
        false -> painterResource(id = R.drawable.rect_lightmode)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    painter = rect,
                    contentDescription = stringResource(id = R.string.rect_description),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
            Text(text = height)
        }
        TextField(
            modifier = Modifier.padding(top = 20.dp),
            label = { Text(stringResource(id = R.string.width))},
            value = width,
            onValueChange = onWidthValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        TextField(
            modifier = Modifier.padding(top = 10.dp),
            label = { Text(stringResource(id = R.string.height))},
            value = height,
            onValueChange = onHeightValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        TextField(
            modifier = Modifier.padding(top = 10.dp),
            label = { Text(stringResource(id = R.string.radius))},
            value = radius,
            onValueChange = onRadiusValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
    }
}

@Composable
fun SphereCalc(
    scale: Int,
    radius: Int,
    darkMode: Boolean
){
    val sphere = when(darkMode){
        true -> painterResource(id = R.drawable.sphere_darkmode)
        false -> painterResource(id = R.drawable.sphere_lightmode)
    }

    val area: Int = (PI * 4 * radius * scale).roundToInt()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = sphere,
                contentDescription = stringResource(id = R.string.sphere),
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                text = stringResource(id = R.string.area) + ": " + area,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    Android_exercisesTheme{
        App()
    }
}
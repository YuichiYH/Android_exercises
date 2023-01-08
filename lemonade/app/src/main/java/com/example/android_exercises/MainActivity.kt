package com.example.android_exercises

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_exercises.ui.theme.Android_exercisesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_exercisesTheme {
                Lemonade()
            }
        }
    }
}


@Preview
@Composable
fun Lemonade() {
    LemonadeMaker(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun LemonadeMaker(modifier: Modifier = Modifier){
    var state: Int by remember {
        mutableStateOf(0)
    }

    val image = when(state%4){
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val description = when(state%4){
        0 -> stringResource(id = R.string.lemon_tree_description)
        1 -> stringResource(id = R.string.lemon_description)
        2 -> stringResource(id = R.string.glass_full_description)
        else -> stringResource(id = R.string.glass_empty_description)
    }

    val text = when(state%4){
        0 -> stringResource(id = R.string.tree)
        1 -> stringResource(id = R.string.lemon_tap)
        2 -> stringResource(id = R.string.lemonade)
        else -> stringResource(id = R.string.refresh)
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = text, fontSize = 18.sp, modifier = Modifier.padding(bottom = 16.dp))
        Button(
            modifier = Modifier.border(
                width = 2.dp,
                color = Color(105, 205, 216),
                shape = RoundedCornerShape(15.dp)
            ),
            onClick = {

                if(state % 4 == 1){
                    if((1..2).random() == 1){
                        state +=1
                    }
                }

                else{
                    state += 1
                }

            }
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = description
            )
        }
    }
}
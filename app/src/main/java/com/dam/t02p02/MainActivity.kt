package com.dam.t02p02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.t02p02.ui.theme.T02p02Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            T02p02Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    appLemonade()
                }
            }
        }
    }
}

@Composable
fun lemonTextAndImage(
    imageResource: Int,
    textResource: Int,
    imageContentDescription: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(Color(0xFFF8E34C))
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.titleLemonade),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold,
                modifier = modifier.padding(15.dp)
            )
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(35.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC2EBD1))
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(imageContentDescription),
                modifier = modifier,
            )
        }
        Spacer(modifier = modifier.height(20.dp))
        Text(
            text = stringResource(textResource),
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        )
    }
}

@Composable
fun appLemonade() {
    var currentStep by remember { mutableStateOf(1) }
    var currentSqueeze by remember { mutableStateOf(0) }

    when (currentStep) {
        1 -> {
            lemonTextAndImage(
                imageResource = R.drawable.lemon_tree,
                textResource = R.string.Tree,
                imageContentDescription = R.string.lemonTree,
                onImageClick = {
                    currentStep = 2
                    currentSqueeze = Random.nextInt(2, 4)
                }
            )
        }

        2 -> {
            lemonTextAndImage(
                imageResource = R.drawable.lemon_squeeze,
                textResource = R.string.Squeeze,
                imageContentDescription = R.string.lemon,
                onImageClick = {
                    currentSqueeze--
                    if (currentSqueeze == 0) {
                        currentStep = 3
                    }
                }
            )
        }

        3 -> {
            lemonTextAndImage(
                imageResource = R.drawable.lemon_drink,
                textResource = R.string.Drink,
                imageContentDescription = R.string.lemonadeGlass,
                onImageClick = {
                    currentStep = 4
                }
            )
        }

        4 -> {
            lemonTextAndImage(
                imageResource = R.drawable.lemon_restart,
                textResource = R.string.Start,
                imageContentDescription = R.string.emptyGlass,
                onImageClick = {
                    currentStep = 1
                }
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    T02p02Theme {
        appLemonade()
    }
}
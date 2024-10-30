package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()

            }
        }
    }
}


@Composable
fun LemonsToLemonade(modifier: Modifier = Modifier) {

    var result by remember { mutableStateOf(1) }

    var tapsRequired = (2..4).random()
    var tapsCounted = 0

    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_restart
    }

    val imageText = when (result) {
        1 -> stringResource(R.string.tap_lemon_tree)
        2 -> stringResource(R.string.keep_tapping)
        3 -> stringResource(R.string.tap_lemonade)
        4 -> stringResource(R.string.tap_empty_glass)
        else -> stringResource(R.string.tap_lemon_tree)
    }

    val imageDesc = when (result) {
        1 -> stringResource(R.string.lemon_tree)
        2 -> stringResource(R.string.lemon)
        3 -> stringResource(R.string.glass_of_lemonade)
        4 -> stringResource(R.string.empty_glass)
        else -> stringResource(R.string.lemon_tree)
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Lemonade",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(color = Color(0xFFf8fa6e))
                .fillMaxWidth()
                .height(52.dp)
                .padding(vertical = 16.dp)
        )

        Spacer(Modifier.height(272.dp))

        Image(
            painter = painterResource(imageResource),
            contentDescription = imageDesc,
            modifier = Modifier
                .size(236.dp)
                .clip(shape = RoundedCornerShape(28.dp))
                .background(color = Color(0xFFb9fae9))
                .padding(4.dp)
                .clickable {

                    if (result == 2) {
                        tapsCounted++
                        if (tapsCounted == tapsRequired) {
                            result = result + 1
                        }

                    } else {
                        result = result + 1

                        if (result > 4) {
                            result = 1
                        }
                    }
                }
        )

        Spacer(Modifier.height(24.dp))

        Text(
            fontSize = 18.sp,
            text = imageText
        )

    }

}


@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonsToLemonade(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    )
}
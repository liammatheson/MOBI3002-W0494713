package com.russ.compose_01

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column // allows multiple rows of text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russ.compose_01.ui.theme.BasicTheme

// Kotlin Reference:  https://kotlinlang.org/docs/home.html
// Compose Reference: https://developer.android.com/jetpack/compose/documentation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowNext by rememberSaveable { mutableStateOf(true) }
    var screenNum = 1

    Surface(color = MaterialTheme.colorScheme.background) {
        Log.d("Compose", "screenNum = $screenNum")

        if (shouldShowNext) {
            Screen_01(onContinueClicked = { shouldShowNext = false; screenNum += 1 })
        } else {
            Screen_02(onContinueClicked = { shouldShowNext = true; screenNum += 1 })
        }
    }
}


@Composable
fun Separator() {
    Text(text = " ================================================ ")
}

@Composable
fun Screen_01(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Box(modifier = Modifier.size(height = 90.dp, width = 290.dp)) {
            Text("TopStart", Modifier.align(Alignment.TopStart))
            Text("TopCenter", Modifier.align(Alignment.TopCenter))
            Text("TopEnd", Modifier.align(Alignment.TopEnd))

            Text("CenterStart", Modifier.align(Alignment.CenterStart))
            Text("Center", Modifier.align(Alignment.Center))
            Text(text = "CenterEnd", Modifier.align(Alignment.CenterEnd))

            Text("BottomStart", Modifier.align(Alignment.BottomStart))
            Text("BottomCenter", Modifier.align(Alignment.BottomCenter))
            Text("BottomEnd", Modifier.align(Alignment.BottomEnd))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = (Modifier.padding(vertical = 24.dp)),
                onClick = onContinueClicked
            ) {
                Text("Other Screen")
            }
        }
    }
}

@Composable
fun Screen_02(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    Column {
        ClickableText(
            text = AnnotatedString(
                " ++++ ShowLayout_04 +++++ "
                        + " ++++ ShowLayout_04 +++++ "
                        + " ++++ ShowLayout_04 +++++ "
                        + " ++++ ShowLayout_04 +++++ "
                        + " ++++ ShowLayout_04 +++++ "
                        + " ++++ ShowLayout_04 +++++ "
                        + " ++++ ShowLayout_04 +++++ "
                        + " ++++ ShowLayout_04 +++++ "
            ),
            maxLines = if (isExpanded) Int.MAX_VALUE else 2,
            overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
            onClick = { isExpanded = !isExpanded; Log.d("Compose2", "isExpanded =" + isExpanded.toString())}
        )
        Button(
            modifier = (Modifier.padding(vertical = 24.dp)),
            onClick = {isExpanded = true; Log.d("Compose3", "isExpanded =" + isExpanded.toString())}
        ) {
            Text("Expand")
        }
        Button(
            modifier = (Modifier.padding(vertical = 24.dp)),
            onClick = onContinueClicked
        ) {
            Text("Other Screen")
        }

//        // add some more to the screen
//        ShowLayout_03();
//        ShowLayout_04();

    }
}


@Composable
fun ShowLayout_03() {
    Column {
        Text(
            text = " ....ShowLayout_03...... ",
            color = Color.Blue,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = " ....ShowLayout_03 Use Style...... ",
            style = TextStyle(
                color = Color.Red,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = " ....MaterialTheme.typography.bodyLarge...... ",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFFD0BCFF)
        )
    }
}

@Composable
fun ShowLayout_04() {
    Text(text = " ++++ ShowLayout_04 +++++ "
            + " ++++ ShowLayout_04 +++++ "
            + " ++++ ShowLayout_04 +++++ "
            + " ++++ ShowLayout_04 +++++ "
            + " ++++ ShowLayout_04 +++++ ",
        maxLines = 2,
        color = Color(0xFF625b71),
        overflow = TextOverflow.Ellipsis,
        onTextLayout = {
            if (it.hasVisualOverflow) {
                Log.d("Compose", "ShowLayout_04: Overflow ")
            }
        }
    )

}
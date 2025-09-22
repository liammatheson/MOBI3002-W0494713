package com.russ.compose_01

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.russ.compose_01.ui.theme.Compose_01Theme // Specifically imported
import androidx.compose.foundation.layout.Column // allows multiple rows of text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Kotlin Reference:  https://kotlinlang.org/docs/home.html
// Compose Reference: https://developer.android.com/jetpack/compose/documentation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout_01()
        }
    }
}

@Composable
fun MainLayout_01() {
    Column {
        Text(text = "Col 1 Row 1 * ")
        Text(text = "Col 1 Row 2 * ")
        Text(text = "Col 1 Row 3 * ")
        Row {
            Text(text = "Col 1 Row 4 * ")
            Text(text = "Col 2 Row 4 * ")
            Text(text = "Col 3 Row 4 * ")
        }
        Separator()
        ShowLayout_02()
        Separator()
        ShowLayout_03()
        Separator()
        ShowLayout_04()
    }
}

// Mouse-over Text to see all the parameters
// "goto" Text function in Kotlin code
//

//          Parameters of Text function...
//    text: String,
//    modifier: Modifier,
//    color: Color,
//    fontSize: TextUnit,
//    fontStyle: FontStyle?,
//    fontWeight: FontWeight?,
//    fontFamily: FontFamily?,
//    letterSpacing: TextUnit,
//    textDecoration: TextDecoration?,
//    textAlign: TextAlign?,
//    lineHeight: TextUnit,
//    overflow: TextOverflow,
//    softWrap: Boolean,
//    maxLines: Int,
//    onTextLayout: (TextLayoutResult) -> Unit,
//    style: TextStyle

@Composable
fun Separator() {
    Text(text = " ================================================ ")
}

@Composable
fun ShowLayout_02() {
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
        Text("RUSS", Modifier.align(Alignment.Center))
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
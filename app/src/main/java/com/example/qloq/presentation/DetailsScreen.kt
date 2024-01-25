package com.example.qloq.presentation

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import java.lang.System.currentTimeMillis

//This module renders the pet details screen
@Composable
fun DetailsScreen(qloSprite: QloSprite, sharedPreferences: SharedPreferences) {

    SideEffect {
        //Side effect is rendered once per composition
        compareTime(qloSprite, sharedPreferences)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF37306B), Color(0xFF66347F), Color(0xFF9E4784))
                )
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 15.dp)
            ) {
                Text(
                    color = Color(0xFFFFFFFF),
                    text = "Details",
                    style = MaterialTheme.typography.title3,
                    fontFamily = FontFamily.Monospace,
                )
                Text(
                    color = Color(0xFFFFFFFF),
                    text = "Name: ${qloSprite.name}",
                    style = MaterialTheme.typography.caption2,
                    fontFamily = FontFamily.Monospace,

                    )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 15.dp)
            ) {
                CustomText(text = "Hunger:")
                CustomLinearProgressBar(progress = (qloSprite.hungerStat.toDouble() / 100).toFloat())
                CustomText(text = "Thirst:")
                CustomLinearProgressBar(progress = (qloSprite.thirstStat.toDouble() / 100).toFloat())
                CustomText(text = "Happiness:")
                CustomLinearProgressBar(progress = (qloSprite.happinessStat.toDouble() / 100).toFloat())
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD27685))

                )
                Spacer(modifier = Modifier.width(width = 2.dp))
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
        }
    }
}

fun compareTime(qloSprite: QloSprite, sharedPreferences: SharedPreferences) {
    val lastTimeSeen = sharedPreferences.getLong("timeStamp", currentTimeMillis())
    val curTime = currentTimeMillis()
    val timeDiff = curTime - lastTimeSeen

    //Now have the delta of time in milliseconds
//    Log.d("Time1", "Time Difference: " + lastTimeSeen)
//    Log.d("Time2", "Time Difference: " + curTime)
//    Log.d("Time3", "Time Difference: " + timeDiff)

    //Qlo Sprite details are updated
    qloSprite.updateStats(timeDiff)

    //Save new decremented Values to storage
    val editor = sharedPreferences.edit()
    editor.putLong("timeStamp", currentTimeMillis())
    editor.putInt("hungerStat", qloSprite.hungerStat)
    editor.putInt("thirstStat", qloSprite.thirstStat)
    editor.putInt("happinessStat", qloSprite.happinessStat)
    editor.apply()
}

@Composable
private fun CustomText(text: String) {
    Text(
        color = Color(0xFFFFFFFF),
        text = text,
        textAlign = TextAlign.Center,
        fontFamily = FontFamily.Monospace,
        fontSize = 13.sp
    )
}

@Composable
private fun CustomLinearProgressBar(progress: Float) {
    var context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            Toast.makeText(context,"${progress.times(100).toInt()}", Toast.LENGTH_SHORT).show()
        }) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
                .padding(end = 15.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color(0xFFFFFFFF)),
            color = Color(0xFFD27685),
        )
    }
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
//Enables Device specific preview of the DetailsScreen in the Default Preview Android Studio window
@Composable
fun DetailsPreview() {
    val sprite = QloSprite("Qlo", 45, 45, 45)
    val context = LocalContext.current
    DetailsScreen(sprite, PreferenceManager.getDefaultSharedPreferences(context))
}
package com.example.qloq.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.preference.PreferenceManager
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.qloq.presentation.theme.QLOQTheme
import java.lang.System.currentTimeMillis

//Initialisation class
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

        // below line is to get to integer values from our shared prefs.
        // If not present set default to 30.
        val hungerStat = sharedPreferences.getInt("hungerStat", 30)
        val thirstStat = sharedPreferences.getInt("thirstStat", 30)
        val happinessStat = sharedPreferences.getInt("happinessStat", 30)

        //Update the Sprite's statistics based on the last time they were seen.
        val sprite = QloSprite("Qlo", hungerStat, thirstStat, happinessStat)
        val lastTimeSeen = sharedPreferences.getLong("timeStamp", currentTimeMillis())
        sprite.updateStats(lastTimeSeen - currentTimeMillis())

        setContent {
            QloqApp(sprite, sharedPreferences)
        }
    }
}

//The initial Qloq application module
@Composable
fun QloqApp(sprite: QloSprite, sharedPreferences: SharedPreferences) {
    QLOQTheme {
        val swipeDismissableNavController = rememberSwipeDismissableNavController()

        SwipeDismissableNavHost(
            navController = swipeDismissableNavController,
            startDestination = "Landing",
        ) {
            composable("Landing") {
                RenderPet(swipeDismissableNavController, sprite, sharedPreferences)
            }
            composable("DetailsScreen") {
                DetailsScreen(sprite, sharedPreferences)
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
//Enables Device specific previews in the Default Preview Android Studio window
@Composable
fun DefaultPreview() {
    val sprite = QloSprite("Qlo", 45, 45, 45)
    val context = LocalContext.current
    QloqApp(sprite, PreferenceManager.getDefaultSharedPreferences(context))
}
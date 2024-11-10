package com.edumate.greenify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.edumate.greenify.core.ui.theme.GreenifyTheme
import com.edumate.greenify.navigation.AdaptiveCoinListDetailPane
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenifyTheme {
                Scaffold { padding ->
                    AdaptiveCoinListDetailPane(
                        modifier = Modifier.padding(top = padding.calculateTopPadding())
                    )
                }
            }
        }
    }
}
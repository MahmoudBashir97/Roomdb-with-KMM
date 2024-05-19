package com.mahmoudbashir.setuproomdb2

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mahmoudbashir.setuproomdb2.database.getUserDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = getUserDatabase(applicationContext)
        setContent {
            App(database)
        }
    }
}

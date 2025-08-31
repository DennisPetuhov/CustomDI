package com.example.customdi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.customdi.di.CustomModule
import com.example.customdi.CustomViewModelFactory
import com.example.customdi.di.CustomDI
import com.example.customdi.ui.theme.CustomDITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var customViewModelFactory: CustomViewModelFactory
        val myViewModel: MyViewModel by viewModels { customViewModelFactory }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val di = CustomDI()
        CustomModule().configure(di)
        customViewModelFactory = CustomViewModelFactory(di)

        setContent {
            CustomDITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android ${myViewModel.getData()}",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomDITheme {
        Greeting("Android")
    }
}
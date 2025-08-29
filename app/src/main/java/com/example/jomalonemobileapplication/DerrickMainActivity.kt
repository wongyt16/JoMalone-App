package com.example.jomalonemobileapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jomalonemobileapplication.ui.theme.JoMaloneMobileApplicationTheme
import com.example.jomalonemobileapplication.ui.theme.Background
import com.example.jomalonemobileapplication.cart.ShoppingCartScreen

class DerrickMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JoMaloneMobileApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    ShoppingCartScreen()
                }
            }
        }
    }
}

@Composable
fun PaymentModule(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){

    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(

    ){ innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
    }
}


@Preview(showBackground = true)
@Composable
fun JoMaloneApplicationPreview() {
    JoMaloneMobileApplicationTheme {
        Greeting("Android")
    }
}
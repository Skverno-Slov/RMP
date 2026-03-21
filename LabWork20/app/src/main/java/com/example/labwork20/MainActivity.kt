package com.example.labwork20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labwork20.screens.Authorize
import com.example.labwork20.screens.Product
import com.example.labwork20.screens.ProductDetail
import com.example.labwork20.screens.ProductList
import com.example.labwork20.screens.Register
import com.example.labwork20.ui.theme.LabWork20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var products = List(10){i -> Product("Продукт$i", 100 + i*10f) }

            val navController = rememberNavController()

            LabWork20Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)){
                        NavHost(navController = navController, startDestination = Screens.Authorize.route){
                            composable(Screens.Authorize.route) {
                                Authorize({navController.navigate(Screens.ProductList.route)}, {navController.navigate(Screens.Register.route)})
                            }
                            composable(Screens.Register.route) {
                                Register({navController.navigate(Screens.Authorize.route)})
                            }
                            composable(Screens.ProductList.route) {
                                ProductList({name, price -> navController.navigate(Screens.ProductDetails.buildRoute(name, price))},products)
                            }
                            composable(Screens.ProductDetails.route,
                                arguments = listOf(
                                    navArgument("name"){ NavType.StringType},
                                    navArgument("price"){ NavType.StringType})){ e ->
                                val name = e.arguments?.getString("name") ?: "default"
                                val price = e.arguments?.getString("price") ?: "default"

                                ProductDetail(Product(name, price.toFloat()))
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Screens(val route: String){
    object Authorize: Screens("Authorize")
    object Register: Screens("Register")
    object ProductList: Screens("ProductList")
    object ProductDetails: Screens("ProductDetails/{name}/{price}"){
        fun buildRoute(name: String, price: String): String{
            return "ProductDetails/$name/$price"
        }
    }
}
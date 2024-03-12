package com.example.vk_products.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.vk_products.data.api.models.Products
import com.example.vk_products.presentation.ui.theme.VK_ProductsTheme
import com.example.vk_products.utils.NetworkResult

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val state = mainViewModel.allProductsResponse.observeAsState().value ?: NetworkResult.Loading()

    when(state) {
        is NetworkResult.Success -> {
            SuccessScreen(state.data ?: listOf(), mainViewModel)
        }
        is NetworkResult.Error -> {
            ErrorScreen(state.message?: "Oh, some error!")
        }
        is NetworkResult.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Red)
    }
}

@Composable
fun LoadingScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Loading()
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Icon(
            modifier = Modifier,
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "",
            tint = Color.Black
        )
    }
}

@Composable
fun SuccessScreen(products: List<Products>, mainViewModel: MainViewModel) {
    LazyColumn(
        modifier = Modifier.padding(20.dp)
    ) {
        items(products) { item ->
            ProductItem(item)
        }
    }
}

@Composable
fun ProductItem(item: Products) {
    val title = item.title ?: ""
    val description = item.description ?: ""

    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .clickable {  }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Image(
                painter = rememberImagePainter(item.thumbnail),
                contentDescription = null,
                modifier = Modifier.size(128.dp))
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Row{
                    Text(text = "Price: ", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    Text(text = item.price.toString(), fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
                Text(text = description, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    VK_ProductsTheme {

    }
}
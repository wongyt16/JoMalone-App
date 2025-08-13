package com.example.jomalonemobileapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jomalonemobileapplication.data.CartItem
import com.example.jomalonemobileapplication.data.SampleData
import com.example.jomalonemobileapplication.ui.theme.JoMaloneMobileApplicationTheme

@Composable
fun CartItemRow(item: CartItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF7CE)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(60.dp)
        )
    }
    Spacer(modifier = Modifier
        .width(8.dp)
        .background(Color(0xFFFFF7CE)))

    Column (modifier = modifier.background(Color(0xFFFFF7CE))){
        Text(text = item.name, fontWeight = FontWeight.Bold)
        Text(text = "Size: ${item.size}", fontSize = 12.sp, color = Color.Gray)
        Text("RM %.2f".format(item.unitPrice))

    }

}

@Composable
fun ShoppingCartScreen(modifier : Modifier = Modifier) {
    LazyColumn{
        items(SampleData.cartItems){ item ->
            CartItemRow(item)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}
@Preview
@Composable
fun ShoppingCartScreenPreview() {
    JoMaloneMobileApplicationTheme {
        ShoppingCartScreen()
    }
}


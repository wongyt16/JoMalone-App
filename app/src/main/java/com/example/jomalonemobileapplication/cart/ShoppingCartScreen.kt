package com.example.jomalonemobileapplication.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jomalonemobileapplication.ui.theme.Background
import com.example.jomalonemobileapplication.ui.theme.Cream
import com.example.jomalonemobileapplication.ui.theme.JoMaloneMobileApplicationTheme

@Composable
fun CartItemRow(
    item: CartItem,
    onCloseClick: () -> Unit = {},
    onQuantityChange: (Int) -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier.fillMaxWidth()
    ) {
    var checked by remember { mutableStateOf(false) }
    var quantity by remember { mutableIntStateOf(1) }

    Card(
        modifier = modifier
            .width(320.dp)
            .border(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            containerColor = Cream
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterVertically)
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = item.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color(0xFFFFF7CE), shape = CircleShape)
                            .clickable { onCloseClick() }
                            .border(1.dp, Color.Gray, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Remove item",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.padding(4.dp))
                }
                Text(
                    text = "Size: ${item.size}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                // Change quantity
                Row() {
                    OutlinedButton(
                        onClick = {
                            if (quantity > 1) {
                                quantity--
                                onQuantityChange(quantity)
                            }
                        },
                        modifier = Modifier.size(24.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            width = 1.dp
                        )
                    ) {
                        Text(
                            text = "-",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }

                    Text(
                        text = quantity.toString(),
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    OutlinedButton(
                        onClick = {
                            if (quantity < 10) {
                                quantity++
                                onQuantityChange(quantity)
                            }
                        },
                        modifier = Modifier.size(24.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White
                        ),
                    ) {
                        Text(
                            text = "+",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "RM %.2f".format(item.unitPrice),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = (Color.Black)
                    )

                    Text(
                        text = "RM %.2f".format(item.totalPrice),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = (Color.Black)
                    )

                    Spacer(modifier = Modifier.padding(2.dp))
                }
            }
        }
    }

}

@Composable
fun ShoppingCartScreen(
    onProceedButtonClicked: () -> Unit = {},
    modifier : Modifier = Modifier
) {

    var checked by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<CartItem?>(null) }
    Surface (
        modifier = modifier
            .background(Background)
            .fillMaxSize()
    ) {
        Column (modifier = modifier.padding(16.dp)) {
            Text(
                text = "Shopping Bag",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "COMPLIMENTARY GIFT WRAP WITH EVERY ORDER",
                fontSize = 12.sp
            )
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = modifier.padding(4.dp)) {
                    items(SampleData.cartItems) { item ->
                        CartItemRow(item)
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Cream)
                        .padding(8.dp)
                        .align(Alignment.TopCenter),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = {checked = !checked}
                        )
                        Text(
                            text = "Select All",
                        )
                    }
                    Text(text = {if (checked) })
                }


                )
            }
            Button(
                onClick = { onProceedButtonClicked() },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)

            ) {
                Text("Proceed to Checkout")
            }
        }
    }
}
@Preview (showBackground = true)
@Composable
fun ShoppingCartScreenPreview() {
    JoMaloneMobileApplicationTheme {
        ShoppingCartScreen()
    }
}


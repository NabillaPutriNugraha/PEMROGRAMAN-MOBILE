package com.example.dicerollerjatpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRoller()
        }
    }
}

@Composable
fun DiceRoller() {

    var number1 by remember { mutableStateOf(0) }
    var number2 by remember { mutableStateOf(0) }
    var resultMessage by remember { mutableStateOf("Tekan Roll untuk memulai!") }

    fun getDiceImage(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = getDiceImage(number1)),
                    contentDescription = null,
                    modifier = Modifier.size(160.dp).padding(4.dp)
                )
                Image(
                    painter = painterResource(id = getDiceImage(number2)),
                    contentDescription = null,
                    modifier = Modifier.size(160.dp).padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    number1 = (1..6).random()
                    number2 = (1..6).random()
                    resultMessage = if (number1 == number2) {
                        "Selamat, anda dapat dadu double!"
                    } else {
                        "Anda belum beruntung"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E88E5)
                ),
                shape = RoundedCornerShape(50),
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                Text(text = "Roll", color = Color.White, fontSize = 18.sp)
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            color = Color.White,
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 6.dp
        ) {
            Text(
                text = resultMessage,
                modifier = Modifier.padding(20.dp),
                color = Color(0xFF0D47A1),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
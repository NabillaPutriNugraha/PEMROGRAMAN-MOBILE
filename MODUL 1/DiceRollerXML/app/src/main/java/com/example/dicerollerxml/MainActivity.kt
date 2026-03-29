package com.example.dicerollerxml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivDiceLeft: ImageView = findViewById(R.id.ivDiceLeft)
        val ivDiceRight: ImageView = findViewById(R.id.ivDiceRight)
        val btnRoll: Button = findViewById(R.id.btnRoll)
        val tvResult: TextView = findViewById(R.id.tvResult)

        btnRoll.setOnClickListener {
            val number1 = (1..6).random()
            val number2 = (1..6).random()

            ivDiceLeft.setImageResource(getDiceImage(number1))
            ivDiceRight.setImageResource(getDiceImage(number2))

            if (number1 == number2) {
                tvResult.text = "Selamat, anda dapat dadu double!"
            } else {
                tvResult.text = "Anda belum beruntung"
            }
        }
    }

    private fun getDiceImage(number: Int): Int {
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
}
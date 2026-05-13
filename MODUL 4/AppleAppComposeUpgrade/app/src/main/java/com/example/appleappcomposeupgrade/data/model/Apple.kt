package com.example.appleappcomposeupgrade.data.model

data class Apple(
    val id: Int,
    val name: String,
    val year: String,
    val specsLabel: String,
    val specsValue: String,
    val imageRes: Int,
    val webUrl: String,
    val description: String = "The latest innovation from Apple with cutting-edge technology."
)
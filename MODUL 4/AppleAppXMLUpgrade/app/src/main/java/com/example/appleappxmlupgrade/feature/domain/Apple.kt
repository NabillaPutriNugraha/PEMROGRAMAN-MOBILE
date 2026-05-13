package com.example.appleappxmlupgrade.feature.domain

data class Apple (
    val id: Int,
    val name: String,
    val year: String,
    val description: String,
    val specsLabel: String,
    val specsValue: String,
    val imageRes: Int,
    val webUrl: String
)
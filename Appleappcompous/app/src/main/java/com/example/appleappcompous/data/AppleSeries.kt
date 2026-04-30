package com.example.appleappcompous.data

import com.example.appleappcompous.R

data class AppleSeries(
    val id: Int,
    val name: String,
    val year: String,
    val specsLabel: String,
    val specsValue: String,
    val imageRes: Int,
    val webUrl: String
) {
    companion object {
        fun getData(): List<AppleSeries> {
            return listOf(
                AppleSeries(1, "iPhone 15 Pro", "2023", "Chip:", "A17 Pro Bionic", R.drawable.iphone15, "https://www.apple.com/iphone-15-pro/"),
                AppleSeries(2, "MacBook Air M3", "2024", "Display:", "Liquid Retina 13.6\"", R.drawable.macbook, "https://www.apple.com/macbook-air/"),
                AppleSeries(3, "iPad Pro M4", "2024", "Screen:", "Ultra Retina XDR", R.drawable.ipad, "https://www.apple.com/ipad-pro/"),
                AppleSeries(4, "Apple Watch Ultra 2", "2023", "Battery:", "Up to 36 hours", R.drawable.watch, "https://www.apple.com/apple-watch-ultra-2/"),
                AppleSeries(5, "AirPods Max", "2020", "Audio:", "Spatial Audio", R.drawable.airpods, "https://www.apple.com/airpods-max/")
            )
        }
    }
}
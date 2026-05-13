package com.example.appleappcomposeupgrade.data.repository

import com.example.appleappcomposeupgrade.R
import com.example.appleappcomposeupgrade.data.model.Apple

object AppleSeries {
    fun getData(): List<Apple> {
        return listOf(
            Apple(1, "iPhone 15 Pro", "2023", "Chip:", "A17 Pro Bionic", R.drawable.iphone15, "https://www.apple.com/iphone-15-pro/"),
            Apple(2, "MacBook Air M3", "2024", "Display:", "Liquid Retina 13.6\"", R.drawable.macbook, "https://www.apple.com/macbook-air/"),
            Apple(3, "iPad Pro M4", "2024", "Screen:", "Ultra Retina XDR", R.drawable.ipad, "https://www.apple.com/ipad-pro/"),
            Apple(4, "Apple Watch Ultra 2", "2023", "Battery:", "Up to 36 hours", R.drawable.watch, "https://www.apple.com/apple-watch-ultra-2/"),
            Apple(5, "AirPods Max", "2020", "Audio:", "Spatial Audio", R.drawable.airpods, "https://www.apple.com/airpods-max/")
        )
    }
}
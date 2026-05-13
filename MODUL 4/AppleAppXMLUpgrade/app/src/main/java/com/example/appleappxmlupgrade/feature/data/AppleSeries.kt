package com.example.appleappxmlupgrade.feature.data

import com.example.appleappxmlupgrade.R
import com.example.appleappxmlupgrade.feature.domain.Apple

object AppleSeries {
    fun getData(): List<Apple> {
        return listOf(
            Apple(
                id = 1,
                name = "iPhone 15 Pro",
                year = "2023",
                description = "The first iPhone with an aerospace‑grade titanium design, using the same alloy as spacecraft. Powered by the A17 Pro chip for next-level gaming performance.",
                specsLabel = "Chip: ",
                specsValue = "A17 Pro Bionic",
                imageRes = R.drawable.iphone15,
                webUrl = "https://www.apple.com/iphone-15-pro/"
            ),
            Apple(
                id = 2,
                name = "MacBook Air M3",
                year = "2024",
                description = "Supercharged by the M3 chip, the world's most popular laptop is even more capable with faster performance and up to 18 hours of battery life.",
                specsLabel = "Display:",
                specsValue = "Liquid Retina 13.6\"",
                imageRes = R.drawable.macbook,
                webUrl = "https://www.apple.com/macbook-air/"
            ),
            Apple(
                id = 3,
                name = "iPad Pro M4",
                year = "2024",
                description = "The thinnest Apple product ever. Featuring the breakthrough Ultra Retina XDR display and the outrageous performance of the new M4 chip.",
                specsLabel = "Screen:",
                specsValue = "Ultra Retina XDR",
                imageRes = R.drawable.ipad,
                webUrl = "https://www.apple.com/ipad-pro/"
            ),
            Apple(
                id = 4,
                name = "Apple Watch Ultra 2",
                year = "2023",
                description = "The most rugged and capable Apple Watch. Now with the S9 SiP for a brighter display and a magical new way to use your watch without touching the screen.",
                specsLabel = "Battery:",
                specsValue = "Up to 36 hours",
                imageRes = R.drawable.watch,
                webUrl = "https://www.apple.com/apple-watch-ultra-2/"
            ),
            Apple(
                id = 5,
                name = "AirPods Max",
                year = "2020",
                description = "A perfect balance of exhilarating high-fidelity audio and the effortless magic of AirPods. The ultimate over-ear listening experience is here.",
                specsLabel = "Audio:",
                specsValue = "Spatial Audio",
                imageRes = R.drawable.airpods,
                webUrl = "https://www.apple.com/airpods-max/"
            )
        )
    }
}
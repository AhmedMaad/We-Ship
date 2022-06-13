package com.maad.weship.company

class Shipment(
    val numberOfPalettes: Int = 0,
    val weight: Double = 0.0,
    val generalDescription: String = "",
    val pickupPort: String = "",
    val deliveryPort: String = "",
    val deliverBefore: String = "",
    val containerType: String = "",
    val companyId: String = "",
    val status: String = "Pending",
    val requestId: String = "",
)
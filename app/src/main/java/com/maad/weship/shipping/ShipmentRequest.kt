package com.maad.weship.shipping

import com.maad.weship.company.Shipment

class ShipmentRequest(
    val request: Shipment = Shipment(),
    val userId: String = "",
    val price: Double = 0.0
)
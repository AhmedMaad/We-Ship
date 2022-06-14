package com.maad.weship.shipping

import android.os.Parcelable
import com.maad.weship.company.Shipment
import kotlinx.parcelize.Parcelize

@Parcelize
class ShipmentRequest(
    val request: Shipment = Shipment(),
    val userId: String = "",
    val price: Double = 0.0,
    val companyName: String = "",
    val status: String = "Pending",
    val shipmentRequestId: String = ""
) : Parcelable
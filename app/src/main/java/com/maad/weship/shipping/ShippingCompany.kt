package com.maad.weship.shipping

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ShippingCompany(
    var email: String = "",
    var username: String = "",
    var password: String = "",
    var companyName: String = "",
    var taxNumber: String = "",
    var commercialRegistration: String = "",
    var companyAddress: String = "",
    var companyPhoneNumber: String = "",
    var companyType: String = "",
    var id: String = "",
    var image: String = ""
) : Parcelable
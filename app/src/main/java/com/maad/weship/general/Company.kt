package com.maad.weship.general

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Company(
    var email: String = "",
    var username: String = "",
    var password: String = "",
    var companyName: String = "",
    var taxNumber: String = "",
    var commercialRegistration: String = "",
    var companyAddress: String = "",
    var companyPhoneNumber: String = "",
    var companyType: String = "",
    var userType: String = "",
    var id: String = "",
    var image: String = "",
    val rating: Double = 5.0
) : Parcelable
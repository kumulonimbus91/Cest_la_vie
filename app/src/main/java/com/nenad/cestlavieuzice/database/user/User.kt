package com.nenad.cestlavieuzice.database.user

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize


@Parcelize
data class User (
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNum: String = ""
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!

        )

    override fun describeContents() = 0

    companion object : Parceler<User> {
        override fun User.write(dest: Parcel, flags: Int) = with(dest) {
            writeString(id)
            writeString(name)
            writeString(email)
            writeString(phoneNum)

        }

        override fun create(source: Parcel): User = User(source)
    }
}
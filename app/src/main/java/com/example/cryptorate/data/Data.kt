package com.example.cryptorate.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CryptoList : Serializable {
        @SerializedName("data")
        @Expose
        val data: List<Datum>? = null

        companion object {
            private const val serialVersionUID = -4369048252305703014L
        }
    }

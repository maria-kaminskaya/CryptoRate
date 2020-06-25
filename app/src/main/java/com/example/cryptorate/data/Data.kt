package com.example.cryptorate.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Data {
//    lateinit var name: String
//    lateinit var symbol: String
//    var price_usd: Double = 0.0
}
    class CryptoList : Serializable {
        @SerializedName("data")
        @Expose
        val data: List<Datum>? = null

        companion object {
            private const val serialVersionUID = -4369048252305703014L
        }
    }

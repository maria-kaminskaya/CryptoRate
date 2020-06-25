package com.example.cryptorate.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Quote : Serializable {
    @SerializedName("USD")
    @Expose
    var uSD: USD? = null

    companion object {
        private const val serialVersionUID = -5780538494495942860L
    }
}
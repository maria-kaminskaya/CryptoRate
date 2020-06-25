package com.example.cryptorate

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptorate.data.Datum
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val intent: Intent = intent
        val datum: Datum = intent.getSerializableExtra("coin") as Datum

        val name: TextView = findViewById(R.id.name)
        val price: TextView = findViewById(R.id.price)
        val date: TextView = findViewById(R.id.date)
        val symbol: TextView = findViewById(R.id.symbol)
        val volume24h: TextView = findViewById(R.id.volume24h)
        val market_cap: TextView = findViewById(R.id.market_cap)
        val change1h: TextView = findViewById(R.id.change1h)
        val change24h: TextView = findViewById(R.id.change24h)
        val change7d: TextView = findViewById(R.id.change7d)

        name.text = datum.name.toString() + " (" + datum.symbol + ")"
        price.text =
            "Price: $" + String.format("%,f", datum.quote?.uSD?.price)
        date.text = "Last Updated: " + datum.lastUpdated?.let { parseDateToddMMyyyy(it) }
        symbol.text = "Symbol: " + datum.symbol
        volume24h.text = "Volume/24h: $" + String.format(
            "%,d",
            datum.quote?.uSD?.volume24h?.let { Math.round(it) }
        )
        market_cap.text = "Market Cap: $" + String.format(
            "%,d",
            datum.quote?.uSD?.marketCap?.let { Math.round(it) }
        )
        change1h.text = String.format(
            "Change 1h: %.2f",
            datum.quote?.uSD?.percentChange1h
        ) + "%"
        change24h.text = String.format(
            "Change 24h: %.2f",
            datum.quote?.uSD?.percentChange24h
        ) + "%"
        change7d.text = String.format(
            "Change 7d: %.2f",
            datum.quote?.uSD?.percentChange7d
        ) + "%"
    }


    private fun parseDateToddMMyyyy(time: String): String {
        val sdf =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        val output =
            SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        output.timeZone = TimeZone.getDefault()
        var date: Date? = null
        try {
            date = sdf.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return output.format(date)
    }
}
package com.example.cryptorate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptorate.adapters.CoinAdapter
import com.example.cryptorate.api.ServerApi
import com.example.cryptorate.api.ServiceGenerator
import com.example.cryptorate.data.CryptoList
import com.example.cryptorate.data.Datum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    var adapter: CoinAdapter? = null
    var apiInterface: ServerApi? = null
    private var recyclerView: RecyclerView? = null
    private var cryptoList: MutableList<Datum>? = null
    private var service = ServiceGenerator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = service.getClient()?.create(ServerApi::class.java)

        initRecyclerView()
        getCoinList()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        cryptoList = ArrayList<Datum>()
        adapter = CoinAdapter(cryptoList as ArrayList<Datum>)
        recyclerView!!.adapter = adapter

        recyclerView!!.layoutManager = LinearLayoutManager(this)
        adapter!!.setClickListener(object : CoinAdapter.ItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("coin", adapter!!.getItem(position))
                startActivity(intent)
            }
        })
    }


    private fun getCoinList() {
        val call2: Call<CryptoList>? = apiInterface?.loadData(100)
        call2?.enqueue(object : Callback<CryptoList?> {
            override fun onResponse(
                call: Call<CryptoList?>,
                response: Response<CryptoList?>
            ) {
                val list: CryptoList? = response.body()
                cryptoList!!.clear()
                cryptoList!!.addAll(list?.data!!)
                adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<CryptoList?>,
                t: Throwable
            ) {
                Toast.makeText(this@MainActivity, "onFailure", Toast.LENGTH_SHORT).show()
                Log.d("XXXX", t.localizedMessage)
                call.cancel()
            }
        })
    }

}
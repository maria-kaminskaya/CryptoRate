package com.example.cryptorate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptorate.R
import com.example.cryptorate.data.Datum


class CoinAdapter internal constructor(data: List<Datum>) :
    RecyclerView.Adapter<CoinAdapter.ViewHolder?>() {
    private val mData: List<Datum>
    private var mClickListener: ItemClickListener? = null

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_rate, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datum: Datum = mData[position]

        val name = holder.name
        name.text = datum.name.toString()
        val symbol = holder.symbol
        symbol.text = datum.symbol
        val price = holder.price
        price.text = "Price: $" + String.format(
            "%,f",
            datum.quote?.uSD?.price
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var name: TextView = itemView.findViewById(R.id.name)
        var symbol: TextView = itemView.findViewById(R.id.symbol)
        var price: TextView = itemView.findViewById(R.id.price)
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): Datum {
        return mData[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // data is passed into the constructor
    init {
        mData = data
    }
}
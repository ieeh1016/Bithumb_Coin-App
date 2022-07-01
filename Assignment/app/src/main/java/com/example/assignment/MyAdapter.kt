package com.example.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val items: ArrayList<Data>): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.item_cointitle.text = items.get(position).cointitle
        holder.opening_price.text = items.get(position).opening_price
        holder.closing_price.text = items.get(position).closing_price
        holder.min_price.text = items.get(position).min_price
        holder.max_price.text = items.get(position).max_price
        holder.units_traded.text = items.get(position).units_traded
        holder.acc_trade_value.text = items.get(position).acc_trade_value
        holder.prev_closing_price.text = items.get(position).prev_closing_price
        holder.units_traded_24H.text = items.get(position).units_traded_24H
        holder.acc_trade_value_24H.text = items.get(position).acc_trade_value_24H
        holder.fluctate_24H.text = items.get(position).fluctate_24H
        holder.fluctate_rate_24H.text = items.get(position).fluctate_rate_24H
        holder.date.text = items.get(position).date



    }

    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) { //카드뷰 레이아웃의 각 테스트뷰 아이디에 각 값을 넣음
        val item_cointitle = itemview.findViewById<TextView>(R.id.item_cointitle)
        val opening_price = itemview.findViewById<TextView>(R.id.item_opening_price)
        val closing_price = itemview.findViewById<TextView>(R.id.item_closing_price)
        val min_price = itemview.findViewById<TextView>(R.id.item_min_price)
        val max_price = itemview.findViewById<TextView>(R.id.item_max_price)
        val units_traded = itemview.findViewById<TextView>(R.id.item_units_traded)
        val acc_trade_value = itemview.findViewById<TextView>(R.id.item_acc_trade_value)
        val prev_closing_price = itemview.findViewById<TextView>(R.id.item_prev_closing_price)
        val units_traded_24H = itemview.findViewById<TextView>(R.id.item_units_traded_24H)
        val acc_trade_value_24H = itemview.findViewById<TextView>(R.id.item_acc_trade_value_24H)
        val fluctate_24H = itemview.findViewById<TextView>(R.id.item_fluctate_24H)
        val fluctate_rate_24H = itemview.findViewById<TextView>(R.id.item_fluctate_rate_24H)
        val date = itemview.findViewById<TextView>(R.id.date)




    }

    override fun getItemCount(): Int {

        return items.size
    }



}

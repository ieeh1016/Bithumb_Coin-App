package com.example.assignment

import org.json.JSONObject

class Data(
    var cointitle: String,
    var opening_price: String,
    var closing_price: String,
    var min_price: String,
    var max_price: String,
    var units_traded: String,
    var acc_trade_value: String,
    var prev_closing_price: String,
    var units_traded_24H: String,
    var acc_trade_value_24H: String,
    var fluctate_24H: String,
    var fluctate_rate_24H: String,
    var date: String,
)
{
    constructor(title: String, date: String, json: JSONObject) : this(
        title,
        json.getString("opening_price"),
        json.getString("closing_price"),
        json.getString("min_price"),
        json.getString("max_price"),
        json.getString("units_traded"),
        json.getString("acc_trade_value"),
        json.getString("prev_closing_price"),
        json.getString("units_traded_24H"),
        json.getString("acc_trade_value_24H"),
        json.getString("fluctate_24H"),
        json.getString("fluctate_rate_24H"),
        date,
    )
}

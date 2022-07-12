package com.example.assignment

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

//직렬화

@Parcelize
@Entity(primaryKeys = arrayOf("cointitle","date"))
data class Data(
    @ColumnInfo(name = "cointitle") var cointitle: String,
    @ColumnInfo(name = "opening_price") var opening_price: String,
    @ColumnInfo(name = "closing_price") var closing_price: String,
    @ColumnInfo(name = "min_price") var min_price: String,
    @ColumnInfo(name = "max_price") var max_price: String,
    @ColumnInfo(name = "units_traded") var units_traded: String,
    @ColumnInfo(name = "acc_trade_value") var acc_trade_value: String,
    @ColumnInfo(name = "prev_closing_price") var prev_closing_price: String,
    @ColumnInfo(name = "units_traded_24H") var units_traded_24H: String,
    @ColumnInfo(name = "acc_trade_value_24H") var acc_trade_value_24H: String,
    @ColumnInfo(name = "fluctate_24H") var fluctate_24H: String,
    @ColumnInfo(name = "fluctate_rate_24H") var fluctate_rate_24H: String,
    @ColumnInfo(name = "date") var date: String,
) : Parcelable {
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

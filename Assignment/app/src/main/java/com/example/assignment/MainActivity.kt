package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var mBinding : ActivityMainBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        thread(start = true) {


            val site = "https://api.bithumb.com/public/ticker/ALL_KRW" // 빗썸API 정보를 가지고 있는 주소

            val url = URL(site)
            val conn = url.openConnection()
            val input = conn.getInputStream()
            val isr = InputStreamReader(input)
            val br = BufferedReader(isr) // 라인 단위로 데이터를 읽어옴

            var str: String? = null  // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
            val buf = StringBuffer()

            do{
                str = br.readLine()

                if(str!=null){
                    buf.append(str)
                }
            }while (str!=null)

            val root = JSONObject(buf.toString()) // 객체로 가져옴

            val status: String = root.getString("status")
            val BTC = root.getJSONObject("data").getJSONObject("BTC")
            val ETH = root.getJSONObject("data").getJSONObject("ETH")

            val BTC_opening_price: String = BTC.getString("opening_price")
            val BTC_closing_price: String = BTC.getString("closing_price")
            val BTC_min_price: String = BTC.getString("min_price")
            val BTC_max_price: String = BTC.getString("max_price")
            val BTC_units_traded: String = BTC.getString("units_traded")
            val BTC_acc_trade_value: String = BTC.getString("acc_trade_value")
            val BTC_prev_closing_price: String = BTC.getString("prev_closing_price")
            val BTC_units_traded_24H: String = BTC.getString("units_traded_24H")
            val BTC_acc_trade_value_24H: String = BTC.getString("acc_trade_value_24H")
            val BTC_fluctate_24H: String = BTC.getString("fluctate_24H")
            val BTC_fluctate_rate_24H: String = BTC.getString("fluctate_rate_24H")

            val ETH_opening_price: String = ETH.getString("opening_price")
            val ETH_closing_price: String = ETH.getString("closing_price")
            val ETH_min_price: String = ETH.getString("min_price")
            val ETH_max_price: String = ETH.getString("max_price")
            val ETH_units_traded: String = ETH.getString("units_traded")
            val ETH_acc_trade_value: String = ETH.getString("acc_trade_value")
            val ETH_prev_closing_price: String = ETH.getString("prev_closing_price")
            val ETH_units_traded_24H: String = ETH.getString("units_traded_24H")
            val ETH_acc_trade_value_24H: String = ETH.getString("acc_trade_value_24H")
            val ETH_fluctate_24H: String = ETH.getString("fluctate_24H")
            val ETH_fluctate_rate_24H: String = ETH.getString("fluctate_rate_24H")

            val items = arrayListOf(
                Data(BTC_opening_price,BTC_closing_price,BTC_min_price,BTC_max_price,BTC_units_traded,BTC_acc_trade_value,BTC_prev_closing_price,BTC_units_traded_24H,BTC_acc_trade_value_24H,BTC_fluctate_24H,BTC_fluctate_rate_24H),
                Data(ETH_opening_price,ETH_closing_price,ETH_min_price,ETH_max_price,ETH_units_traded,ETH_acc_trade_value,ETH_prev_closing_price,ETH_units_traded_24H,ETH_acc_trade_value_24H,ETH_fluctate_24H,ETH_fluctate_rate_24H),
                )
            binding.root.post{
                binding.recyclerviewMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                binding.recyclerviewMain.setHasFixedSize(true)
                binding.recyclerviewMain.adapter = MyAdapter(items)
            }

        }

    }

}



package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 택스트 뷰 생성
        textView.text = ""

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


            runOnUiThread { //화면출력

                val opening_price: String = BTC.getString("opening_price")
                val closing_price: String = BTC.getString("closing_price")
                val min_price: String = BTC.getString("min_price")
                val max_price: String = BTC.getString("max_price")
                val units_traded: String = BTC.getString("units_traded")
                val acc_trade_value: String = BTC.getString("acc_trade_value")
                val prev_closing_price: String = BTC.getString("prev_closing_price")
                val units_traded_24H: String = BTC.getString("units_traded_24H")
                val acc_trade_value_24H: String = BTC.getString("acc_trade_value_24H")
                val fluctate_24H: String = BTC.getString("fluctate_24H")
                val fluctate_rate_24H: String = BTC.getString("fluctate_rate_24H")


                textView.append("시가 00시 기준 : ${opening_price}\n\n\n")
                textView.append("종가 00시 기준 : ${closing_price}\n\n\n")
                textView.append("저가 00시 기준 : ${min_price}\n\n\n")
                textView.append("고가 00시 기준 : ${max_price}\n\n\n")
                textView.append("거래량 00시 기준 : ${units_traded}\n\n\n")
                textView.append("거래금액 00시 기준 : ${acc_trade_value}\n\n\n")
                textView.append("전일종가 : ${prev_closing_price}\n\n\n")
                textView.append("최근 24시간 거래량 : ${units_traded_24H}\n\n\n")
                textView.append("최근 24시간 거래금액 : ${acc_trade_value_24H}\n\n\n")
                textView.append("최근 24시간 변동가 : ${fluctate_24H}\n\n\n")
                textView.append("최근 24시간 변동률 : ${fluctate_rate_24H}\n\n\n")





            }
        }
    }

}



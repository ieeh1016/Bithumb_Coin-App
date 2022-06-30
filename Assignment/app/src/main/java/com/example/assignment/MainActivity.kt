package com.example.assignment

import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ListAdapter
import android.widget.TextView
import com.example.assignment.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction



class MainActivity : AppCompatActivity() {
    var mBinding : ActivityMainBinding ? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState : Bundle ? ) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thread = NetworkThread()
        thread.start()
        thread.join()

    }
    inner class NetworkThread: Thread(){
        override fun run() {



            val site = "https://api.bithumb.com/public/ticker/ALL_KRW" // 빗썸API 정보를 가지고 있는 주소

            val url = URL(site)
            val conn = url.openConnection()
            val input = conn.getInputStream()
            val isr = InputStreamReader(input)
            val br = BufferedReader(isr) // 라인 단위로 데이터를 읽어옴

            var str : String ? = null  // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
            val buf = StringBuffer()

            do {
                str = br.readLine()

                if (str != null) {
                    buf.append(str)
                }
            } while (str != null)

            val root = JSONObject(buf.toString()) // 객체로 가져옴

            val status : String = root.getString("status")
            val BTC = root.getJSONObject("data").getJSONObject("BTC")
            val ETH = root.getJSONObject("data").getJSONObject("ETH")
            val ETC = root.getJSONObject("data").getJSONObject("ETC")
            val XRP = root.getJSONObject("data").getJSONObject("XRP")
            val BCH = root.getJSONObject("data").getJSONObject("BCH")
            val QTUM = root.getJSONObject("data").getJSONObject("QTUM")
            val BTG = root.getJSONObject("data").getJSONObject("BTG")
            val EOS = root.getJSONObject("data").getJSONObject("EOS")
            val ICX = root.getJSONObject("data").getJSONObject("ICX")
            val TRX = root.getJSONObject("data").getJSONObject("TRX")




            val BTC_opening_price : String = BTC.getString("opening_price")
            val BTC_closing_price : String = BTC.getString("closing_price")
            val BTC_min_price : String = BTC.getString("min_price")
            val BTC_max_price : String = BTC.getString("max_price")
            val BTC_units_traded : String = BTC.getString("units_traded")
            val BTC_acc_trade_value : String = BTC.getString("acc_trade_value")
            val BTC_prev_closing_price : String = BTC.getString("prev_closing_price")
            val BTC_units_traded_24H : String = BTC.getString("units_traded_24H")
            val BTC_acc_trade_value_24H : String = BTC.getString("acc_trade_value_24H")
            val BTC_fluctate_24H : String = BTC.getString("fluctate_24H")
            val BTC_fluctate_rate_24H : String = BTC.getString("fluctate_rate_24H")

            val ETH_opening_price : String = ETH.getString("opening_price")
            val ETH_closing_price : String = ETH.getString("closing_price")
            val ETH_min_price : String = ETH.getString("min_price")
            val ETH_max_price : String = ETH.getString("max_price")
            val ETH_units_traded : String = ETH.getString("units_traded")
            val ETH_acc_trade_value : String = ETH.getString("acc_trade_value")
            val ETH_prev_closing_price : String = ETH.getString("prev_closing_price")
            val ETH_units_traded_24H : String = ETH.getString("units_traded_24H")
            val ETH_acc_trade_value_24H : String = ETH.getString("acc_trade_value_24H")
            val ETH_fluctate_24H : String = ETH.getString("fluctate_24H")
            val ETH_fluctate_rate_24H : String = ETH.getString("fluctate_rate_24H")

            val ETC_opening_price : String = ETC.getString("opening_price")
            val ETC_closing_price : String = ETC.getString("closing_price")
            val ETC_min_price : String = ETC.getString("min_price")
            val ETC_max_price : String = ETC.getString("max_price")
            val ETC_units_traded : String = ETC.getString("units_traded")
            val ETC_acc_trade_value : String = ETC.getString("acc_trade_value")
            val ETC_prev_closing_price : String = ETC.getString("prev_closing_price")
            val ETC_units_traded_24H : String = ETC.getString("units_traded_24H")
            val ETC_acc_trade_value_24H : String = ETC.getString("acc_trade_value_24H")
            val ETC_fluctate_24H : String = ETC.getString("fluctate_24H")
            val ETC_fluctate_rate_24H : String = ETC.getString("fluctate_rate_24H")

            val XRP_opening_price : String = XRP.getString("opening_price")
            val XRP_closing_price : String = XRP.getString("closing_price")
            val XRP_min_price : String = XRP.getString("min_price")
            val XRP_max_price : String = XRP.getString("max_price")
            val XRP_units_traded : String = XRP.getString("units_traded")
            val XRP_acc_trade_value : String = XRP.getString("acc_trade_value")
            val XRP_prev_closing_price : String = XRP.getString("prev_closing_price")
            val XRP_units_traded_24H : String = XRP.getString("units_traded_24H")
            val XRP_acc_trade_value_24H : String = XRP.getString("acc_trade_value_24H")
            val XRP_fluctate_24H : String = XRP.getString("fluctate_24H")
            val XRP_fluctate_rate_24H : String = XRP.getString("fluctate_rate_24H")

            val BCH_opening_price : String = BCH.getString("opening_price")
            val BCH_closing_price : String = BCH.getString("closing_price")
            val BCH_min_price : String = BCH.getString("min_price")
            val BCH_max_price : String = BCH.getString("max_price")
            val BCH_units_traded : String = BCH.getString("units_traded")
            val BCH_acc_trade_value : String = BCH.getString("acc_trade_value")
            val BCH_prev_closing_price : String = BCH.getString("prev_closing_price")
            val BCH_units_traded_24H : String = BCH.getString("units_traded_24H")
            val BCH_acc_trade_value_24H : String = BCH.getString("acc_trade_value_24H")
            val BCH_fluctate_24H : String = BCH.getString("fluctate_24H")
            val BCH_fluctate_rate_24H : String = BCH.getString("fluctate_rate_24H")

            val QTUM_opening_price : String = QTUM.getString("opening_price")
            val QTUM_closing_price : String = QTUM.getString("closing_price")
            val QTUM_min_price : String = QTUM.getString("min_price")
            val QTUM_max_price : String = QTUM.getString("max_price")
            val QTUM_units_traded : String = QTUM.getString("units_traded")
            val QTUM_acc_trade_value : String = QTUM.getString("acc_trade_value")
            val QTUM_prev_closing_price : String = QTUM.getString("prev_closing_price")
            val QTUM_units_traded_24H : String = QTUM.getString("units_traded_24H")
            val QTUM_acc_trade_value_24H : String = QTUM.getString("acc_trade_value_24H")
            val QTUM_fluctate_24H : String = QTUM.getString("fluctate_24H")
            val QTUM_fluctate_rate_24H : String = QTUM.getString("fluctate_rate_24H")

            val BTG_opening_price : String = BTG.getString("opening_price")
            val BTG_closing_price : String = BTG.getString("closing_price")
            val BTG_min_price : String = BTG.getString("min_price")
            val BTG_max_price : String = BTG.getString("max_price")
            val BTG_units_traded : String = BTG.getString("units_traded")
            val BTG_acc_trade_value : String = BTG.getString("acc_trade_value")
            val BTG_prev_closing_price : String = BTG.getString("prev_closing_price")
            val BTG_units_traded_24H : String = BTG.getString("units_traded_24H")
            val BTG_acc_trade_value_24H : String = BTG.getString("acc_trade_value_24H")
            val BTG_fluctate_24H : String = BTG.getString("fluctate_24H")
            val BTG_fluctate_rate_24H : String = BTG.getString("fluctate_rate_24H")

            val EOS_opening_price : String = EOS.getString("opening_price")
            val EOS_closing_price : String = EOS.getString("closing_price")
            val EOS_min_price : String = EOS.getString("min_price")
            val EOS_max_price : String = EOS.getString("max_price")
            val EOS_units_traded : String = EOS.getString("units_traded")
            val EOS_acc_trade_value : String = EOS.getString("acc_trade_value")
            val EOS_prev_closing_price : String = EOS.getString("prev_closing_price")
            val EOS_units_traded_24H : String = EOS.getString("units_traded_24H")
            val EOS_acc_trade_value_24H : String = EOS.getString("acc_trade_value_24H")
            val EOS_fluctate_24H : String = EOS.getString("fluctate_24H")
            val EOS_fluctate_rate_24H : String = EOS.getString("fluctate_rate_24H")

            val ICX_opening_price : String = ICX.getString("opening_price")
            val ICX_closing_price : String = ICX.getString("closing_price")
            val ICX_min_price : String = ICX.getString("min_price")
            val ICX_max_price : String = ICX.getString("max_price")
            val ICX_units_traded : String = ICX.getString("units_traded")
            val ICX_acc_trade_value : String = ICX.getString("acc_trade_value")
            val ICX_prev_closing_price : String = ICX.getString("prev_closing_price")
            val ICX_units_traded_24H : String = ICX.getString("units_traded_24H")
            val ICX_acc_trade_value_24H : String = ICX.getString("acc_trade_value_24H")
            val ICX_fluctate_24H : String = ICX.getString("fluctate_24H")
            val ICX_fluctate_rate_24H : String = ICX.getString("fluctate_rate_24H")

            val TRX_opening_price : String = TRX.getString("opening_price")
            val TRX_closing_price : String = TRX.getString("closing_price")
            val TRX_min_price : String = TRX.getString("min_price")
            val TRX_max_price : String = TRX.getString("max_price")
            val TRX_units_traded : String = TRX.getString("units_traded")
            val TRX_acc_trade_value : String = TRX.getString("acc_trade_value")
            val TRX_prev_closing_price : String = TRX.getString("prev_closing_price")
            val TRX_units_traded_24H : String = TRX.getString("units_traded_24H")
            val TRX_acc_trade_value_24H : String = TRX.getString("acc_trade_value_24H")
            val TRX_fluctate_24H : String = TRX.getString("fluctate_24H")
            val TRX_fluctate_rate_24H : String = TRX.getString("fluctate_rate_24H")

            val items = arrayListOf(
                Data("BTC", "시가 00시 기준: "+BTC_opening_price, "종가 00시 기준: "+BTC_closing_price, "저가 00시 기준: "+BTC_min_price, "고가 00시 기준: "+BTC_max_price, "거래량 00시 기준: "+BTC_units_traded, "거래금액 00시 기준: "+BTC_acc_trade_value, "전일종가: "+BTC_prev_closing_price, "최근 24시간 거래량: "+BTC_units_traded_24H, "최근 24시간 거래금액: "+BTC_acc_trade_value_24H, "최근 24시간 변동가: "+BTC_fluctate_24H, "최근 24시간 변동률: "+BTC_fluctate_rate_24H),
                Data("ETH", "시가 00시 기준: "+ETH_opening_price, "종가 00시 기준: "+ETH_closing_price, "저가 00시 기준: "+ETH_min_price, "고가 00시 기준: "+ETH_max_price, "거래량 00시 기준: "+ETH_units_traded, "거래금액 00시 기준: "+ETH_acc_trade_value, "전일종가: "+ETH_prev_closing_price, "최근 24시간 거래량: "+ETH_units_traded_24H, "최근 24시간 거래금액: "+ETH_acc_trade_value_24H, "최근 24시간 변동가: "+ETH_fluctate_24H, "최근 24시간 변동률: "+ETH_fluctate_rate_24H),
                Data("ETC", "시가 00시 기준: "+ETC_opening_price, "종가 00시 기준: "+ETC_closing_price, "저가 00시 기준: "+ETC_min_price, "고가 00시 기준: "+ETC_max_price, "거래량 00시 기준: "+ETC_units_traded, "거래금액 00시 기준: "+ETC_acc_trade_value, "전일종가: "+ETC_prev_closing_price, "최근 24시간 거래량: "+ETC_units_traded_24H, "최근 24시간 거래금액: "+ETC_acc_trade_value_24H, "최근 24시간 변동가: "+ETC_fluctate_24H, "최근 24시간 변동률: "+ETC_fluctate_rate_24H),
                Data("XRP", "시가 00시 기준: "+XRP_opening_price, "종가 00시 기준: "+XRP_closing_price, "저가 00시 기준: "+XRP_min_price, "고가 00시 기준: "+XRP_max_price, "거래량 00시 기준: "+XRP_units_traded, "거래금액 00시 기준: "+XRP_acc_trade_value, "전일종가: "+XRP_prev_closing_price, "최근 24시간 거래량: "+XRP_units_traded_24H, "최근 24시간 거래금액: "+XRP_acc_trade_value_24H, "최근 24시간 변동가: "+XRP_fluctate_24H, "최근 24시간 변동률: "+XRP_fluctate_rate_24H),
                Data("BCH", "시가 00시 기준: "+BCH_opening_price, "종가 00시 기준: "+BCH_closing_price, "저가 00시 기준: "+BCH_min_price, "고가 00시 기준: "+BCH_max_price, "거래량 00시 기준: "+BCH_units_traded, "거래금액 00시 기준: "+BCH_acc_trade_value, "전일종가: "+BCH_prev_closing_price, "최근 24시간 거래량: "+BCH_units_traded_24H, "최근 24시간 거래금액: "+BCH_acc_trade_value_24H, "최근 24시간 변동가: "+BCH_fluctate_24H, "최근 24시간 변동률: "+BCH_fluctate_rate_24H),
                Data("QTUM", "시가 00시 기준: "+QTUM_opening_price, "종가 00시 기준: "+QTUM_closing_price, "저가 00시 기준: "+QTUM_min_price, "고가 00시 기준: "+QTUM_max_price, "거래량 00시 기준: "+QTUM_units_traded, "거래금액 00시 기준: "+QTUM_acc_trade_value, "전일종가: "+QTUM_prev_closing_price, "최근 24시간 거래량: "+QTUM_units_traded_24H, "최근 24시간 거래금액: "+QTUM_acc_trade_value_24H, "최근 24시간 변동가: "+QTUM_fluctate_24H, "최근 24시간 변동률: "+QTUM_fluctate_rate_24H),
                Data("BTG", "시가 00시 기준: "+BTG_opening_price, "종가 00시 기준: "+BTG_closing_price, "저가 00시 기준: "+BTG_min_price, "고가 00시 기준: "+BTG_max_price, "거래량 00시 기준: "+BTG_units_traded, "거래금액 00시 기준: "+BTG_acc_trade_value, "전일종가: "+BTG_prev_closing_price, "최근 24시간 거래량: "+BTG_units_traded_24H, "최근 24시간 거래금액: "+BTG_acc_trade_value_24H, "최근 24시간 변동가: "+BTG_fluctate_24H, "최근 24시간 변동률: "+BTG_fluctate_rate_24H),
                Data("EOS", "시가 00시 기준: "+EOS_opening_price, "종가 00시 기준: "+EOS_closing_price, "저가 00시 기준: "+EOS_min_price, "고가 00시 기준: "+EOS_max_price, "거래량 00시 기준: "+EOS_units_traded, "거래금액 00시 기준: "+EOS_acc_trade_value, "전일종가: "+EOS_prev_closing_price, "최근 24시간 거래량: "+EOS_units_traded_24H, "최근 24시간 거래금액: "+EOS_acc_trade_value_24H, "최근 24시간 변동가: "+EOS_fluctate_24H, "최근 24시간 변동률: "+EOS_fluctate_rate_24H),
                Data("ICX", "시가 00시 기준: "+ICX_opening_price, "종가 00시 기준: "+ICX_closing_price, "저가 00시 기준: "+ICX_min_price, "고가 00시 기준: "+ICX_max_price, "거래량 00시 기준: "+ICX_units_traded, "거래금액 00시 기준: "+ICX_acc_trade_value, "전일종가: "+ICX_prev_closing_price, "최근 24시간 거래량: "+ICX_units_traded_24H, "최근 24시간 거래금액: "+ICX_acc_trade_value_24H, "최근 24시간 변동가: "+ICX_fluctate_24H, "최근 24시간 변동률: "+ICX_fluctate_rate_24H),
                Data("TRX","시가 00시 기준: "+TRX_opening_price,"종가 00시 기준: "+TRX_closing_price,"저가 00시 기준: "+TRX_min_price,"고가 00시 기준: "+TRX_max_price,"거래량 00시 기준: "+TRX_units_traded,"거래금액 00시 기준: "+TRX_acc_trade_value,"전일종가: "+TRX_prev_closing_price,"최근 24시간 거래량: "+TRX_units_traded_24H,"최근 24시간 거래금액: "+TRX_acc_trade_value_24H,"최근 24시간 변동가: "+TRX_fluctate_24H,"최근 24시간 변동률: "+TRX_fluctate_rate_24H),
                //Data("ELF",ELF_opening_price,ELF_closing_price,ELF_min_price,ELF_max_price,ELF_units_traded,ELF_acc_trade_value,ELF_prev_closing_price,ELF_units_traded_24H,ELF_acc_trade_value_24H,ELF_fluctate_24H,ELF_fluctate_rate_24H),
                //Data("OMG",OMG_opening_price,OMG_closing_price,OMG_min_price,OMG_max_price,OMG_units_traded,OMG_acc_trade_value,OMG_prev_closing_price,OMG_units_traded_24H,OMG_acc_trade_value_24H,OMG_fluctate_24H,OMG_fluctate_rate_24H),
                //Data("KNC",KNC_opening_price,KNC_closing_price,KNC_min_price,KNC_max_price,KNC_units_traded,KNC_acc_trade_value,KNC_prev_closing_price,KNC_units_traded_24H,KNC_acc_trade_value_24H,KNC_fluctate_24H,KNC_fluctate_rate_24H),
                //Data("GLM",GLM_opening_price,GLM_closing_price,GLM_min_price,GLM_max_price,GLM_units_traded,GLM_acc_trade_value,GLM_prev_closing_price,GLM_units_traded_24H,GLM_acc_trade_value_24H,GLM_fluctate_24H,GLM_fluctate_rate_24H),
                //Data("ZIL",ZIL_opening_price,ZIL_closing_price,ZIL_min_price,ZIL_max_price,ZIL_units_traded,ZIL_acc_trade_value,ZIL_prev_closing_price,ZIL_units_traded_24H,ZIL_acc_trade_value_24H,ZIL_fluctate_24H,ZIL_fluctate_rate_24H),
                //Data("WAXP",WAXP_opening_price,WAXP_closing_price,WAXP_min_price,WAXP_max_price,WAXP_units_traded,WAXP_acc_trade_value,WAXP_prev_closing_price,WAXP_units_traded_24H,WAXP_acc_trade_value_24H,WAXP_fluctate_24H,WAXP_fluctate_rate_24H),
                //Data("POWR",POWR_opening_price,POWR_closing_price,POWR_min_price,POWR_max_price,POWR_units_traded,POWR_acc_trade_value,POWR_prev_closing_price,POWR_units_traded_24H,POWR_acc_trade_value_24H,POWR_fluctate_24H,POWR_fluctate_rate_24H),
                //Data("LRC",LRC_opening_price,LRC_closing_price,LRC_min_price,LRC_max_price,LRC_units_traded,LRC_acc_trade_value,LRC_prev_closing_price,LRC_units_traded_24H,LRC_acc_trade_value_24H,LRC_fluctate_24H,LRC_fluctate_rate_24H),
                //Data("STEEM",STEEM_opening_price,STEEM_closing_price,STEEM_min_price,STEEM_max_price,STEEM_units_traded,STEEM_acc_trade_value,STEEM_prev_closing_price,STEEM_units_traded_24H,STEEM_acc_trade_value_24H,STEEM_fluctate_24H,STEEM_fluctate_rate_24H),
                //Data("STRAX",STRAX_opening_price,STRAX_closing_price,STRAX_min_price,STRAX_max_price,STRAX_units_traded,STRAX_acc_trade_value,STRAX_prev_closing_price,STRAX_units_traded_24H,STRAX_acc_trade_value_24H,STRAX_fluctate_24H,STRAX_fluctate_rate_24H),
                //Data("ZRX",ZRX_opening_price,ZRX_closing_price,ZRX_min_price,ZRX_max_price,ZRX_units_traded,ZRX_acc_trade_value,ZRX_prev_closing_price,ZRX_units_traded_24H,ZRX_acc_trade_value_24H,ZRX_fluctate_24H,ZRX_fluctate_rate_24H),

            )

            binding.root.post{

                binding.recyclerviewMain.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
                binding.recyclerviewMain.setHasFixedSize(true)
                binding.recyclerviewMain.adapter = MyAdapter(items)

                val btn = findViewById<Button>(R.id.reflesh_button) as Button
                btn.setOnClickListener(View.OnClickListener {
                    Toast.makeText(this@MainActivity, "갱신되었습니다.", Toast.LENGTH_SHORT).show()
                    MyAdapter(items).notifyDataSetChanged()
                    //scrollToPosition(0)
                })


            }


        }
    }



}



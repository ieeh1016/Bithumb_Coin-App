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
            val date: String =  root.getJSONObject("data").getString("date").toString()
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
            val ELF = root.getJSONObject("data").getJSONObject("ELF")
            val OMG = root.getJSONObject("data").getJSONObject("OMG")
            val KNC = root.getJSONObject("data").getJSONObject("KNC")
            val GLM = root.getJSONObject("data").getJSONObject("GLM")
            val ZIL = root.getJSONObject("data").getJSONObject("ZIL")
            val WAXP = root.getJSONObject("data").getJSONObject("WAXP")
            val POWR = root.getJSONObject("data").getJSONObject("POWR")
            val LRC = root.getJSONObject("data").getJSONObject("LRC")



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

            val ELF_opening_price : String = ELF.getString("opening_price")
            val ELF_closing_price : String = ELF.getString("closing_price")
            val ELF_min_price : String = ELF.getString("min_price")
            val ELF_max_price : String = ELF.getString("max_price")
            val ELF_units_traded : String = ELF.getString("units_traded")
            val ELF_acc_trade_value : String = ELF.getString("acc_trade_value")
            val ELF_prev_closing_price : String = ELF.getString("prev_closing_price")
            val ELF_units_traded_24H : String = ELF.getString("units_traded_24H")
            val ELF_acc_trade_value_24H : String = ELF.getString("acc_trade_value_24H")
            val ELF_fluctate_24H : String = ELF.getString("fluctate_24H")
            val ELF_fluctate_rate_24H : String = ELF.getString("fluctate_rate_24H")

            val OMG_opening_price : String = OMG.getString("opening_price")
            val OMG_closing_price : String = OMG.getString("closing_price")
            val OMG_min_price : String = OMG.getString("min_price")
            val OMG_max_price : String = OMG.getString("max_price")
            val OMG_units_traded : String = OMG.getString("units_traded")
            val OMG_acc_trade_value : String = OMG.getString("acc_trade_value")
            val OMG_prev_closing_price : String = OMG.getString("prev_closing_price")
            val OMG_units_traded_24H : String = OMG.getString("units_traded_24H")
            val OMG_acc_trade_value_24H : String = OMG.getString("acc_trade_value_24H")
            val OMG_fluctate_24H : String = OMG.getString("fluctate_24H")
            val OMG_fluctate_rate_24H : String = OMG.getString("fluctate_rate_24H")

            val KNC_opening_price : String = KNC.getString("opening_price")
            val KNC_closing_price : String = KNC.getString("closing_price")
            val KNC_min_price : String = KNC.getString("min_price")
            val KNC_max_price : String = KNC.getString("max_price")
            val KNC_units_traded : String = KNC.getString("units_traded")
            val KNC_acc_trade_value : String = KNC.getString("acc_trade_value")
            val KNC_prev_closing_price : String = KNC.getString("prev_closing_price")
            val KNC_units_traded_24H : String = KNC.getString("units_traded_24H")
            val KNC_acc_trade_value_24H : String = KNC.getString("acc_trade_value_24H")
            val KNC_fluctate_24H : String = KNC.getString("fluctate_24H")
            val KNC_fluctate_rate_24H : String = KNC.getString("fluctate_rate_24H")

            val GLM_opening_price : String = GLM.getString("opening_price")
            val GLM_closing_price : String = GLM.getString("closing_price")
            val GLM_min_price : String = GLM.getString("min_price")
            val GLM_max_price : String = GLM.getString("max_price")
            val GLM_units_traded : String = GLM.getString("units_traded")
            val GLM_acc_trade_value : String = GLM.getString("acc_trade_value")
            val GLM_prev_closing_price : String = GLM.getString("prev_closing_price")
            val GLM_units_traded_24H : String = GLM.getString("units_traded_24H")
            val GLM_acc_trade_value_24H : String = GLM.getString("acc_trade_value_24H")
            val GLM_fluctate_24H : String = GLM.getString("fluctate_24H")
            val GLM_fluctate_rate_24H : String = GLM.getString("fluctate_rate_24H")

            val ZIL_opening_price : String = ZIL.getString("opening_price")
            val ZIL_closing_price : String = ZIL.getString("closing_price")
            val ZIL_min_price : String = ZIL.getString("min_price")
            val ZIL_max_price : String = ZIL.getString("max_price")
            val ZIL_units_traded : String = ZIL.getString("units_traded")
            val ZIL_acc_trade_value : String = ZIL.getString("acc_trade_value")
            val ZIL_prev_closing_price : String = ZIL.getString("prev_closing_price")
            val ZIL_units_traded_24H : String = ZIL.getString("units_traded_24H")
            val ZIL_acc_trade_value_24H : String = ZIL.getString("acc_trade_value_24H")
            val ZIL_fluctate_24H : String = ZIL.getString("fluctate_24H")
            val ZIL_fluctate_rate_24H : String = ZIL.getString("fluctate_rate_24H")

            val WAXP_opening_price : String = WAXP.getString("opening_price")
            val WAXP_closing_price : String = WAXP.getString("closing_price")
            val WAXP_min_price : String = WAXP.getString("min_price")
            val WAXP_max_price : String = WAXP.getString("max_price")
            val WAXP_units_traded : String = WAXP.getString("units_traded")
            val WAXP_acc_trade_value : String = WAXP.getString("acc_trade_value")
            val WAXP_prev_closing_price : String = WAXP.getString("prev_closing_price")
            val WAXP_units_traded_24H : String = WAXP.getString("units_traded_24H")
            val WAXP_acc_trade_value_24H : String = WAXP.getString("acc_trade_value_24H")
            val WAXP_fluctate_24H : String = WAXP.getString("fluctate_24H")
            val WAXP_fluctate_rate_24H : String = WAXP.getString("fluctate_rate_24H")

            val POWR_opening_price : String = POWR.getString("opening_price")
            val POWR_closing_price : String = POWR.getString("closing_price")
            val POWR_min_price : String = POWR.getString("min_price")
            val POWR_max_price : String = POWR.getString("max_price")
            val POWR_units_traded : String = POWR.getString("units_traded")
            val POWR_acc_trade_value : String = POWR.getString("acc_trade_value")
            val POWR_prev_closing_price : String = POWR.getString("prev_closing_price")
            val POWR_units_traded_24H : String = POWR.getString("units_traded_24H")
            val POWR_acc_trade_value_24H : String = POWR.getString("acc_trade_value_24H")
            val POWR_fluctate_24H : String = POWR.getString("fluctate_24H")
            val POWR_fluctate_rate_24H : String = POWR.getString("fluctate_rate_24H")

            val LRC_opening_price : String = LRC.getString("opening_price")
            val LRC_closing_price : String = LRC.getString("closing_price")
            val LRC_min_price : String = LRC.getString("min_price")
            val LRC_max_price : String = LRC.getString("max_price")
            val LRC_units_traded : String = LRC.getString("units_traded")
            val LRC_acc_trade_value : String = LRC.getString("acc_trade_value")
            val LRC_prev_closing_price : String = LRC.getString("prev_closing_price")
            val LRC_units_traded_24H : String = LRC.getString("units_traded_24H")
            val LRC_acc_trade_value_24H : String = LRC.getString("acc_trade_value_24H")
            val LRC_fluctate_24H : String = LRC.getString("fluctate_24H")
            val LRC_fluctate_rate_24H : String = LRC.getString("fluctate_rate_24H")

            val items = arrayListOf(
                Data("BTC", "시가 00시 기준: "+BTC_opening_price, "종가 00시 기준: "+BTC_closing_price, "저가 00시 기준: "+BTC_min_price, "고가 00시 기준: "+BTC_max_price, "거래량 00시 기준: "+BTC_units_traded, "거래금액 00시 기준: "+BTC_acc_trade_value, "전일종가: "+BTC_prev_closing_price, "최근 24시간 거래량: "+BTC_units_traded_24H, "최근 24시간 거래금액: "+BTC_acc_trade_value_24H, "최근 24시간 변동가: "+BTC_fluctate_24H, "최근 24시간 변동률: "+BTC_fluctate_rate_24H,"TimeStamp: "+date),
                Data("ETH", "시가 00시 기준: "+ETH_opening_price, "종가 00시 기준: "+ETH_closing_price, "저가 00시 기준: "+ETH_min_price, "고가 00시 기준: "+ETH_max_price, "거래량 00시 기준: "+ETH_units_traded, "거래금액 00시 기준: "+ETH_acc_trade_value, "전일종가: "+ETH_prev_closing_price, "최근 24시간 거래량: "+ETH_units_traded_24H, "최근 24시간 거래금액: "+ETH_acc_trade_value_24H, "최근 24시간 변동가: "+ETH_fluctate_24H, "최근 24시간 변동률: "+ETH_fluctate_rate_24H,"TimeStamp: "+date),
                Data("ETC", "시가 00시 기준: "+ETC_opening_price, "종가 00시 기준: "+ETC_closing_price, "저가 00시 기준: "+ETC_min_price, "고가 00시 기준: "+ETC_max_price, "거래량 00시 기준: "+ETC_units_traded, "거래금액 00시 기준: "+ETC_acc_trade_value, "전일종가: "+ETC_prev_closing_price, "최근 24시간 거래량: "+ETC_units_traded_24H, "최근 24시간 거래금액: "+ETC_acc_trade_value_24H, "최근 24시간 변동가: "+ETC_fluctate_24H, "최근 24시간 변동률: "+ETC_fluctate_rate_24H,"TimeStamp: "+date),
                Data("XRP", "시가 00시 기준: "+XRP_opening_price, "종가 00시 기준: "+XRP_closing_price, "저가 00시 기준: "+XRP_min_price, "고가 00시 기준: "+XRP_max_price, "거래량 00시 기준: "+XRP_units_traded, "거래금액 00시 기준: "+XRP_acc_trade_value, "전일종가: "+XRP_prev_closing_price, "최근 24시간 거래량: "+XRP_units_traded_24H, "최근 24시간 거래금액: "+XRP_acc_trade_value_24H, "최근 24시간 변동가: "+XRP_fluctate_24H, "최근 24시간 변동률: "+XRP_fluctate_rate_24H,"TimeStamp: "+date),
                Data("BCH", "시가 00시 기준: "+BCH_opening_price, "종가 00시 기준: "+BCH_closing_price, "저가 00시 기준: "+BCH_min_price, "고가 00시 기준: "+BCH_max_price, "거래량 00시 기준: "+BCH_units_traded, "거래금액 00시 기준: "+BCH_acc_trade_value, "전일종가: "+BCH_prev_closing_price, "최근 24시간 거래량: "+BCH_units_traded_24H, "최근 24시간 거래금액: "+BCH_acc_trade_value_24H, "최근 24시간 변동가: "+BCH_fluctate_24H, "최근 24시간 변동률: "+BCH_fluctate_rate_24H,"TimeStamp: "+date),
                Data("QTUM", "시가 00시 기준: "+QTUM_opening_price, "종가 00시 기준: "+QTUM_closing_price, "저가 00시 기준: "+QTUM_min_price, "고가 00시 기준: "+QTUM_max_price, "거래량 00시 기준: "+QTUM_units_traded, "거래금액 00시 기준: "+QTUM_acc_trade_value, "전일종가: "+QTUM_prev_closing_price, "최근 24시간 거래량: "+QTUM_units_traded_24H, "최근 24시간 거래금액: "+QTUM_acc_trade_value_24H, "최근 24시간 변동가: "+QTUM_fluctate_24H, "최근 24시간 변동률: "+QTUM_fluctate_rate_24H,"TimeStamp: "+date),
                Data("BTG", "시가 00시 기준: "+BTG_opening_price, "종가 00시 기준: "+BTG_closing_price, "저가 00시 기준: "+BTG_min_price, "고가 00시 기준: "+BTG_max_price, "거래량 00시 기준: "+BTG_units_traded, "거래금액 00시 기준: "+BTG_acc_trade_value, "전일종가: "+BTG_prev_closing_price, "최근 24시간 거래량: "+BTG_units_traded_24H, "최근 24시간 거래금액: "+BTG_acc_trade_value_24H, "최근 24시간 변동가: "+BTG_fluctate_24H, "최근 24시간 변동률: "+BTG_fluctate_rate_24H,"TimeStamp: "+date),
                Data("EOS", "시가 00시 기준: "+EOS_opening_price, "종가 00시 기준: "+EOS_closing_price, "저가 00시 기준: "+EOS_min_price, "고가 00시 기준: "+EOS_max_price, "거래량 00시 기준: "+EOS_units_traded, "거래금액 00시 기준: "+EOS_acc_trade_value, "전일종가: "+EOS_prev_closing_price, "최근 24시간 거래량: "+EOS_units_traded_24H, "최근 24시간 거래금액: "+EOS_acc_trade_value_24H, "최근 24시간 변동가: "+EOS_fluctate_24H, "최근 24시간 변동률: "+EOS_fluctate_rate_24H,"TimeStamp: "+date),
                Data("ICX", "시가 00시 기준: "+ICX_opening_price, "종가 00시 기준: "+ICX_closing_price, "저가 00시 기준: "+ICX_min_price, "고가 00시 기준: "+ICX_max_price, "거래량 00시 기준: "+ICX_units_traded, "거래금액 00시 기준: "+ICX_acc_trade_value, "전일종가: "+ICX_prev_closing_price, "최근 24시간 거래량: "+ICX_units_traded_24H, "최근 24시간 거래금액: "+ICX_acc_trade_value_24H, "최근 24시간 변동가: "+ICX_fluctate_24H, "최근 24시간 변동률: "+ICX_fluctate_rate_24H,"TimeStamp: "+date),
                Data("TRX","시가 00시 기준: "+TRX_opening_price,"종가 00시 기준: "+TRX_closing_price,"저가 00시 기준: "+TRX_min_price,"고가 00시 기준: "+TRX_max_price,"거래량 00시 기준: "+TRX_units_traded,"거래금액 00시 기준: "+TRX_acc_trade_value,"전일종가: "+TRX_prev_closing_price,"최근 24시간 거래량: "+TRX_units_traded_24H,"최근 24시간 거래금액: "+TRX_acc_trade_value_24H,"최근 24시간 변동가: "+TRX_fluctate_24H,"최근 24시간 변동률: "+TRX_fluctate_rate_24H,"TimeStamp: "+date),
                Data("ELF","시가 00시 기준: "+ELF_opening_price,"종가 00시 기준: "+ELF_closing_price,"저가 00시 기준: "+ELF_min_price,"고가 00시 기준: "+ELF_max_price,"거래량 00시 기준: "+ELF_units_traded,"거래금액 00시 기준: "+ELF_acc_trade_value,"전일종가: "+ELF_prev_closing_price,"최근 24시간 거래량: "+ELF_units_traded_24H,"최근 24시간 거래금액: "+ELF_acc_trade_value_24H,"최근 24시간 변동가: "+ELF_fluctate_24H,"최근 24시간 변동률: "+ELF_fluctate_rate_24H,"TimeStamp: "+date),
                Data("OMG","시가 00시 기준: "+OMG_opening_price,"종가 00시 기준: "+OMG_closing_price,"저가 00시 기준: "+OMG_min_price,"고가 00시 기준: "+OMG_max_price,"거래량 00시 기준: "+OMG_units_traded,"거래금액 00시 기준: "+OMG_acc_trade_value,"전일종가: "+OMG_prev_closing_price,"최근 24시간 거래량: "+OMG_units_traded_24H,"최근 24시간 거래금액: "+OMG_acc_trade_value_24H,"최근 24시간 변동가: "+OMG_fluctate_24H,"최근 24시간 변동률: "+OMG_fluctate_rate_24H,"TimeStamp: "+date),
                Data("KNC","시가 00시 기준: "+KNC_opening_price,"종가 00시 기준: "+KNC_closing_price,"저가 00시 기준: "+KNC_min_price,"고가 00시 기준: "+KNC_max_price,"거래량 00시 기준: "+KNC_units_traded,"거래금액 00시 기준: "+KNC_acc_trade_value,"전일종가: "+KNC_prev_closing_price,"최근 24시간 거래량: "+KNC_units_traded_24H,"최근 24시간 거래금액: "+KNC_acc_trade_value_24H,"최근 24시간 변동가: "+KNC_fluctate_24H,"최근 24시간 변동률: "+KNC_fluctate_rate_24H,"TimeStamp: "+date),
                Data("GLM","시가 00시 기준: "+GLM_opening_price,"종가 00시 기준: "+GLM_closing_price,"저가 00시 기준: "+GLM_min_price,"고가 00시 기준: "+GLM_max_price,"거래량 00시 기준: "+GLM_units_traded,"거래금액 00시 기준: "+GLM_acc_trade_value,"전일종가: "+GLM_prev_closing_price,"최근 24시간 거래량: "+GLM_units_traded_24H,"최근 24시간 거래금액: "+GLM_acc_trade_value_24H,"최근 24시간 변동가: "+GLM_fluctate_24H,"최근 24시간 변동률: "+GLM_fluctate_rate_24H,"TimeStamp: "+date),
                Data("ZIL","시가 00시 기준: "+ZIL_opening_price,"종가 00시 기준: "+ZIL_closing_price,"저가 00시 기준: "+ZIL_min_price,"고가 00시 기준: "+ZIL_max_price,"거래량 00시 기준: "+ZIL_units_traded,"거래금액 00시 기준: "+ZIL_acc_trade_value,"전일종가: "+ZIL_prev_closing_price,"최근 24시간 거래량: "+ZIL_units_traded_24H,"최근 24시간 거래금액: "+ZIL_acc_trade_value_24H,"최근 24시간 변동가: "+ZIL_fluctate_24H,"최근 24시간 변동률: "+ZIL_fluctate_rate_24H,"TimeStamp: "+date),
                Data("WAXP","시가 00시 기준: "+WAXP_opening_price,"종가 00시 기준: "+WAXP_closing_price,"저가 00시 기준: "+WAXP_min_price,"고가 00시 기준: "+WAXP_max_price,"거래량 00시 기준: "+WAXP_units_traded,"거래금액 00시 기준: "+WAXP_acc_trade_value,"전일종가: "+WAXP_prev_closing_price,"최근 24시간 거래량: "+WAXP_units_traded_24H,"최근 24시간 거래금액: "+WAXP_acc_trade_value_24H,"최근 24시간 변동가: "+WAXP_fluctate_24H,"최근 24시간 변동률: "+WAXP_fluctate_rate_24H,"TimeStamp: "+date),
                Data("POWR","시가 00시 기준: "+POWR_opening_price,"종가 00시 기준: "+POWR_closing_price,"저가 00시 기준: "+POWR_min_price,"고가 00시 기준: "+POWR_max_price,"거래량 00시 기준: "+POWR_units_traded,"거래금액 00시 기준: "+POWR_acc_trade_value,"전일종가: "+POWR_prev_closing_price,"최근 24시간 거래량: "+POWR_units_traded_24H,"최근 24시간 거래금액: "+POWR_acc_trade_value_24H,"최근 24시간 변동가: "+POWR_fluctate_24H,"최근 24시간 변동률: "+POWR_fluctate_rate_24H,"TimeStamp: "+date),
                Data("LRC","시가 00시 기준: "+LRC_opening_price,"종가 00시 기준: "+LRC_closing_price,"저가 00시 기준: "+LRC_min_price,"고가 00시 기준: "+LRC_max_price,"거래량 00시 기준: "+LRC_units_traded,"거래금액 00시 기준: "+LRC_acc_trade_value,"전일종가: "+LRC_prev_closing_price,"최근 24시간 거래량: "+LRC_units_traded_24H,"최근 24시간 거래금액: "+LRC_acc_trade_value_24H,"최근 24시간 변동가: "+LRC_fluctate_24H,"최근 24시간 변동률: "+LRC_fluctate_rate_24H,"TimeStamp: "+date),
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
                    //.scrollToPosition(0)
                })


            }


        }
    }



}



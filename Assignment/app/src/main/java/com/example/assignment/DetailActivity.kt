package com.example.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    private var _item: Data? = null
    private val item get() = _item!!


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        _item = intent?.getParcelableExtra("item")

        //상단 제목
        val cointitle_intent = item.cointitle
        val cointitle: TextView = findViewById(R.id.detail_cointitle)
        cointitle.text = "$cointitle_intent 상세"

        //뒤로가기 버튼
        val button = findViewById<Button>(R.id.back_button)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                onBackPressed()
            }
        })

        //데이터 받아와서 출력한다.
        binding.detailItemOpeningPrice.text = "시가: ${item.opening_price} 원"
        binding.detailItemClosingPrice.text = "현재가: ${item.closing_price} 원 "
        binding.detailItemMinPrice.text = "현재시간 기준 저가: ${item.min_price} 원"
        binding.detailItemMaxPrice.text = "현재시간 기준 고가: ${item.max_price} 원"
        binding.detailItemUnitsTraded.text = "현재시간 기준 거래량: ${item.units_traded}"
        binding.detailItemAccTradeValue.text = "현재시간 기준 거래금액: ${item.acc_trade_value} 원"
        binding.detailItemPrevClosingPrice.text = "전일종가: ${item.closing_price} 원"
        binding.detailItemUnitsTraded24H.text = "최근 24시간 거래량: ${item.units_traded_24H}"
        binding.detailItemAccTradeValue24H.text = "최근 24시간 거래금액: ${item.acc_trade_value_24H} 원"
        binding.detailItemFluctate24H.text = "최근 24시간 변동가: ${item.fluctate_24H} 원"
        binding.detailItemFluctateRate24H.text = "최근 24시간 변동률: ${item.fluctate_rate_24H}"

    }
}


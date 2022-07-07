package com.example.assignment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.CardviewdetailLayoutBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


class MyDetailAdapter() : ListAdapter<Data, MyDetailAdapter.MyViewHolder>(object : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.cointitle == newItem.cointitle
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.date == newItem.date
    }
})

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(CardviewdetailLayoutBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)


        with(holder.binding) {
            detailTitle.text = "${item.cointitle}"
            detailItemOpeningPrice.text = "시가: ${item.opening_price} 원"
            detailItemClosingPrice.text = "현재가: ${item.closing_price} 원"
            detailItemMinPrice.text = "저가 00시 기준: ${item.min_price}"
            detailItemMaxPrice.text = "고가 00시 기준: ${item.max_price}"
            detailItemUnitsTraded.text = "거래량 00시 기준: ${item.units_traded}"
            detailItemAccTradeValue.text = "거래금액 00시 기준: ${item.acc_trade_value}"
            detailItemPrevClosingPrice.text = "전일종가: ${item.prev_closing_price}"
            detailItemUnitsTraded24H.text = "최근 24시간 거래량: ${item.units_traded_24H}"
            detailItemAccTradeValue24H.text = "최근 24시간 거래금액: ${item.acc_trade_value_24H}"
            detailItemFluctate24H.text = "최근 24시간 변동가: ${item.fluctate_24H}"
            detailItemFluctateRate24H.text = "최근 24시간 변동률: ${item.fluctate_rate_24H}"
            detailDate.text = "Timestamp: ${item.date}"

        }
    }

    class MyViewHolder(val binding: CardviewdetailLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // ViewHolder 의 생성자 매개변수로 binding 을 받도록 함
    // RecyclerView.ViewHolder 의 생성자 매개변수는 View 이므로 Binding 의 root 를 넘겨줌

}

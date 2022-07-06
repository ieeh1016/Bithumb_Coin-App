package com.example.assignment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.CardviewLayoutBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


class MyAdapter() : ListAdapter<Data, MyAdapter.MyViewHolder>(object : DiffUtil.ItemCallback<Data>() {
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
        return MyViewHolder(CardviewLayoutBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)


        with(holder.binding) {
            itemCointitle.text = "코인 Name : ${item.cointitle}"
            itemOpeningPrice.text = "시가 00시 기준: ${item.opening_price}"
            itemClosingPrice.text = "종가 00시 기준: ${item.closing_price}"
            itemMinPrice.text = "저가 00시 기준: ${item.min_price}"
            itemMaxPrice.text = "고가 00시 기준: ${item.max_price}"
            itemUnitsTraded.text = "거래량 00시 기준: ${item.units_traded}"
            itemAccTradeValue.text = "거래금액 00시 기준: ${item.acc_trade_value}"
            itemPrevClosingPrice.text = "전일종가: ${item.prev_closing_price}"
            itemUnitsTraded24H.text = "최근 24시간 거래량: ${item.units_traded_24H}"
            itemAccTradeValue24H.text = "최근 24시간 거래금액: ${item.acc_trade_value_24H}"
            itemFluctate24H.text = "최근 24시간 변동가: ${item.fluctate_24H}"
            itemFluctateRate24H.text = "최근 24시간 변동률: ${item.fluctate_rate_24H}"
            date.text = "Timestamp: ${item.date}"
        }
    }

    class MyViewHolder(val binding: CardviewLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    // ViewHolder 의 생성자 매개변수로 binding 을 받도록 함
    // RecyclerView.ViewHolder 의 생성자 매개변수는 View 이므로 Binding 의 root 를 넘겨줌

}

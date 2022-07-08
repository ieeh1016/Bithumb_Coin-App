package com.example.assignment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.CardviewLayoutBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

//ListAdapter와 DiffUtil 을 이용하여 수정이 필요한 부분만 갱신시킴으로써 갱신할때마다 adapter를 set하여 스크롤이 가장 위로 올라가는 부분을 해결하였다.
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
            itemCointitle.text = "${item.cointitle}"
            itemOpeningPrice.text = "시가: ${item.opening_price} 원"
            itemClosingPrice.text = "현재가: ${item.closing_price} 원"
            itemUnitsTraded24H.text = "거래량: ${item.units_traded_24H}"
            itemDate.text = "TimeStamp: ${item.date}"

            root.setOnClickListener {
                itemClickListener?.onClick(item)
            }
        }
    }

    interface onItemClickListener{
        fun onClick(item:Data)
    }
    fun setItemClickListener(onItemClickListener: onItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private var itemClickListener: onItemClickListener? = null

    class MyViewHolder(val binding: CardviewLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // ViewHolder 의 생성자 매개변수로 binding 을 받도록 함
    // RecyclerView.ViewHolder 의 생성자 매개변수는 View 이므로 Binding 의 root 를 넘겨줌

}

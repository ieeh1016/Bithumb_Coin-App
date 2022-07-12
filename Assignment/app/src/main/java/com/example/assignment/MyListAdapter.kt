package com.example.assignment


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.CardviewlistLayoutBinding
import java.sql.Timestamp
import java.text.SimpleDateFormat


class MyListAdapter() : ListAdapter<Data, MyListAdapter.MyViewHolder>(object : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.closing_price == newItem.closing_price
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.date == newItem.date
    }
})

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(CardviewlistLayoutBinding.inflate(layoutInflater, parent, false))
    }

    private fun covertTimestampToDate(timestamp: String): String{
        val sdf = SimpleDateFormat("yyyy.MM.dd / hh:mm:ss")
        val date = sdf.format(timestamp.toLong())
        return date.toString()
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        val time = covertTimestampToDate(item.date)
        with(holder.binding) {
            itemListDate.text = "${time}"
            itemListClosingPrice.text = "${item.closing_price} 원"

            root.setOnClickListener {
                itemClickListener?.onClick(item)
        }

        }
    }
    interface onItemClickListener {
        fun onClick(item: Data)
    }

    fun setItemClickListener(onItemClickListener: onItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private var itemClickListener: onItemClickListener? = null

    class MyViewHolder(val binding: CardviewlistLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // ViewHolder 의 생성자 매개변수로 binding 을 받도록 함
    // RecyclerView.ViewHolder 의 생성자 매개변수는 View 이므로 Binding 의 root 를 넘겨줌

}

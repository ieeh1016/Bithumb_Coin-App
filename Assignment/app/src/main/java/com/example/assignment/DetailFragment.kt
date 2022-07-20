package com.example.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.annotation.SuppressLint
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var _item: Data? = arguments?.getParcelable("item")
    private val item get() = _item!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        _item = arguments?.getParcelable("item")
        return binding.root
    }


    private fun backFragment(){
        activity?.supportFragmentManager!!.beginTransaction().remove(this).commit()
        activity?.supportFragmentManager!!.popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailBackButton.setOnClickListener {
            val fragment2 = ListFragment()
            val bundle = Bundle()
            bundle.putParcelable("item",item)
            fragment2.arguments = bundle
            backFragment()
        }

        binding.detailCointitle.text = "${item.cointitle} 상세"

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
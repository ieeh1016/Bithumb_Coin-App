package com.example.assignment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.assignment.databinding.ActivityListBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat

class ListActivity : AppCompatActivity() {

    private val lineChart: LineChart by lazy { binding.linechart }

    private var _item: Data? = null
    private val item get() = _item!!

    private val binding by lazy { ActivityListBinding.inflate(layoutInflater) }

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "DataDB103"
        ).build()
    }

    private val adapter by lazy {
        MyListAdapter().apply {
            setItemClickListener(object : MyListAdapter.onItemClickListener {
                override fun onClick(item: Data) {
                    // 데이터 자체를 DetailActivity 로 넘겨줌
                    val intent = Intent(this@ListActivity, DetailActivity::class.java).apply {
                        putExtra("item", item)
                    }
                    startActivity(intent)
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //setContentView에는 binding.root 를 전달.

        _item = intent.getParcelableExtra("item")
        if (savedInstanceState != null) {
            _item = savedInstanceState.getParcelable("item")
        }


        //상단 제목
        val cointitle_intent = item.cointitle
        val cointitle: TextView = findViewById(R.id.list_cointitle)
        cointitle.text = cointitle_intent

        //뒤로가기 버튼
        val button = findViewById<Button>(R.id.back_button)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                onBackPressed()
            }
        })

        with(binding) { //Non-nullable 수신 객체이고 결과가 필요하지 않을때 with 을 사용함.
            recyclerviewList.apply {
                layoutManager =
                    LinearLayoutManager(this@ListActivity) // Recycler view layout manager 설정
                setHasFixedSize(true)
                adapter = this@ListActivity.adapter
            }

            // refresh 버튼 리스너 설정
            refleshButton.setOnClickListener {
                lifecycleScope.launch {
                    //새로고침 버튼을 누르면 데이터를 Insert하고 UI를 새롭게 갱신 하고 chart도 갱신한다.
                    insertRefresh()
                    chart()
                }
            }

        }

        //화면이 전환되면 선택한 ITEM에 데이터만 가져와 UI를 띄워준다.
        lifecycleScope.launch {
            uiRefresh()
            chart()
        }
    }

    //화면 돌리기 오류 제거
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("item", item)
    }

    //Timestamp -> Date 변환기
    private fun covertTimestampToDate(timestamp: String): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd / hh:mm:ss")
        val date = sdf.format(timestamp.toLong())
        return date.toString()
    }


    private suspend fun insertRefresh() = withContext(Dispatchers.IO) {
        with(db.DataDao()) {
            val newData = runData()
            insertAll(*newData.toTypedArray())
            val data2 = item.cointitle
            val data = getQueryAll(data2)
            withContext(Dispatchers.Main) {
                adapter.submitList(data)
            }
        }
    }


    private suspend fun uiRefresh() = withContext(Dispatchers.IO) {
        with(db.DataDao()) {
            val data2 = item.cointitle
            val data = getQueryAll(data2)
            withContext(Dispatchers.Main) {
                adapter.submitList(data)
            }
        }
    }


    private suspend fun chart() = withContext(Dispatchers.Main) {

        with(db.DataDao()) {

            val datelist = mutableListOf<String>()
            val pricelist = mutableListOf<String>()

            val data2 = item.cointitle
            val Data = getItem(data2) // List 생성

            for (data in Data) {
                datelist.add(data.date)
            }
            for (data in Data) {
                pricelist.add(data.closing_price)
            }

            val entries = ArrayList<Entry>()

            for (i in 0 until pricelist.size) {
                entries.add(Entry(i.toFloat(), pricelist[i].toFloat()))
            }

            val labels = ArrayList<String>()

            for (i in 0 until datelist.size) {
                labels.add(covertTimestampToDate(datelist[i]))
            }

            val dataset = LineDataSet(entries, "가격 추이")
            dataset.valueTextSize = 20F
            dataset.color = Color.BLACK
            dataset.setCircleColor(Color.RED)
            dataset.setCircleHoleColor(Color.RED)

            lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            lineChart.getTransformer(YAxis.AxisDependency.LEFT)
            lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            lineChart.xAxis.textSize = 12.5f
            lineChart.description.isEnabled = false

            val data = LineData(dataset)
            lineChart.data = data
            lineChart.legend.textSize = 15F
            lineChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            lineChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            lineChart.setVisibleXRangeMaximum(2f)
            lineChart.setVisibleXRangeMinimum(2f)
            lineChart.xAxis.granularity = 1f
            lineChart.invalidate()

        }
    }

    private suspend fun runData(): List<Data> = withContext(Dispatchers.IO) {
        val site = "https://api.bithumb.com/public/ticker/ALL_KRW" // 빗썸API 정보를 가지고 있는 주소
        val url = URL(site)
        val conn = url.openConnection()
        val input = conn.getInputStream()
        val isr = InputStreamReader(input)
        val br = BufferedReader(isr)
        var str: String? = null
        val buf = StringBuffer()
        // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
        do {
            str = br.readLine()
            if (str != null) {
                buf.append(str)
            }
        } while (str != null)


        val root = JSONObject(buf.toString())       // 객체로 가져옴
        val data = root.getJSONObject("data")  //data먼저 객체로가져옴
        val names =
            data.names() ?: JSONArray()     // json(코인들의 이름)이 들어있는 array를 만듬 , data의 코인이름을 전부 가져옴
        val date = data.getString("date")     // date는 먼저 다른 코인처럼 요소가 없기떄문에 따로 가져옴.
        val items = arrayListOf<Data>() // Data의 요소를 가지는 arrayList를 생성

        for (i in (0 until names.length())) { // name의 길이(비트코인데이터양)의 -1 번까지 반복
            val name = names.getString(i)       // data 의 요소 이름 (코인 이름)
            if (name.equals("date")) continue       // data 의 요소 이름이 date 이면(비트코인이 아니면) 건너뛴다.
            val element = data.getJSONObject(name)  // 요소 이름에 해당하는 JSON 객체 가져옴
            items.add(Data(name, date, element))    // 가져온 Data타입의 요소들을 포함하여 items(arrayList)로 담음
        }
        return@withContext items
    }
}
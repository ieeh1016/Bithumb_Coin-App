package com.example.assignment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) } //by lazy를 사용해서 처음 호출될 때 초기화 되도록 설정한다. (by lazy = 처음 선언할때 바로 초기화(할당))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //setContentView에는 binding.root 를 전달.


        NetworkThread { // 최초 앱 실행시 데이터를 보여주기 위해 NetworkThread 호출
            binding.recyclerviewMain.adapter = MyAdapter(it)
        }.start()

        with(binding) { //Non-nullable 수신 객체이고 결과가 필요하지 않을때 with 을 사용함.
            recyclerviewMain.layoutManager = LinearLayoutManager(this@MainActivity) // Recycler view layout manager 설정
            recyclerviewMain.setHasFixedSize(true)

            // refresh 버튼 리스너 설정 (NetworkThread 호출)
            refleshButton.setOnClickListener {
                NetworkThread {
                    recyclerviewMain.adapter = MyAdapter(it)
                    Toast.makeText(this@MainActivity, "갱신되었습니다.", Toast.LENGTH_SHORT).show()
                }.start()
            }
        }

    }

    class NetworkThread(private val callback: ((items: List<Data>) -> Unit)) : Thread() {
        private val Handler = Handler(Looper.getMainLooper())  // Main thread 에 접근하기 위한 handler



        override fun run() {
            val site = "https://api.bithumb.com/public/ticker/ALL_KRW" // 빗썸API 정보를 가지고 있는 주소
            val url = URL(site)
            val conn = url.openConnection()
            val input = conn.getInputStream()
            val isr = InputStreamReader(input)
            val br = BufferedReader(isr)
            var str : String? = null
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
            val names = data.names()?: JSONArray()     // json(코인들의 이름)이 들어있는 array를 만듬 , data의 코인이름을 전부 가져옴
            val date = data.getString("date")     // date는 먼저 다른 코인처럼 요소가 없기떄문에 따로 가져옴.
            val items = arrayListOf<Data>() // Data의 요소를 가지는 arrayList를 생성

            for (i in (0 until names.length())) { // name의 길이(비트코인데이터양)의 -1 번까지 반복
                val name = names.getString(i)       // data 의 요소 이름 (코인 이름)
                if (name.equals("date")) continue       // data 의 요소 이름이 date 이면 건너뛴다.
                val element = data.getJSONObject(name)  // 요소 이름에 해당하는 JSON 객체 가져옴
                items.add(Data(name, date, element))    // 가져온 Data타입의 요소들을 포함하여 items(arrayList)로 담음
            }


            Handler.post{ //UI작업은 무조건 메인쓰레드에서 해야됨 Handler.post 사용
                callback?.invoke(items) //콜백 함수
            }
        }
    }
}



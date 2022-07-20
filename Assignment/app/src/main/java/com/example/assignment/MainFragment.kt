package com.example.assignment

import android.os.Bundle
import android.os.Parcel
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignment.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase


    private val adapter by lazy {
        MyAdapter().apply {
            setItemClickListener(object : MyAdapter.onItemClickListener {
                override fun onClick(item: Data) {
                    Log.d("데이타","${item}")

                    val fragment2 = ListFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("item",item)
                    fragment2.arguments = bundle
                    val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_main, fragment2)
                    transaction.addToBackStack(null).commit()
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Room.databaseBuilder(requireActivity().applicationContext, AppDatabase::class.java, "DataDB104")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    lifecycleScope.launch {
                        insertRefresh() //(데이터를 다시 읽어와서 room에 삽입하고 Edit에 쓰여진 query를 포함한 코인데이터로 UI를  갱신)
                    }
                }
            }).build()
        with(binding) {
            recyclerviewMain.apply {
                layoutManager = LinearLayoutManager(this@MainFragment.context) // Recycler view layout manager 설정
                setHasFixedSize(true)
                adapter = this@MainFragment.adapter
            }

            //refresh 버튼 리스너 설정 - 새로고침 버튼을 누를때마다 refresh함수 실행
            refleshButton.setOnClickListener {
                lifecycleScope.launch {
                    insertRefresh()
                }
            }

            //에디터에 글을 하나씩입력할때마다 room에 저장된 데이터에서 에디터에서 쓴 query를 포함한 코인데이터로 UI를 갱신
            searchEditText.addTextChangedListener {
                lifecycleScope.launch {
                    uiRefresh()
                }
            }
        }
    }

    // 상세 화면에서 새로고침을 하고 다시 Main 화면으로 돌아온 경우 새로고침된 데이터를 보여줄 수 있도록 onResume 에서 firstRefresh 함수 호출한다
    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            uiRefresh() // room에 저장된 데이터를 edit에 쓰여진 query가 포함된 코인데이터로 UI를 갱신시킨다.
        }
    }


    //API에서 읽어온 데이터를 ROOM에 삽입하고 EDIT에서 작성한 string이 이름으로 포함된 코인으로 UI를 갱신한다.
    private suspend fun insertRefresh() = withContext(Dispatchers.IO) {
        val query = withContext(Dispatchers.Main) {
            binding.searchEditText.text.toString()
        }
        with(db.DataDao()) {
            //데이터를 runData()를 이용하여 api로 부터 데이터를 다시 읽어온다
            val newData = runData()
            insertAll(*newData.toTypedArray())
            //edit에 쓰여진 string을 포함한 코인을 select한다.
            val data = getAll(query)
            //ROOM에 저장된 데이터 UI로 갱신시킨다. - UI갱신이므로 Dispatchers.Main에서 작업
            withContext(Dispatchers.Main) {
                adapter.submitList(data)
            }
        }
    }

    private suspend fun uiRefresh() = withContext(Dispatchers.IO) {
        val query = withContext(Dispatchers.Main) {
            binding.searchEditText.text.toString()
        }
        with(db.DataDao()) {
            val data = getAll(query)
            withContext(Dispatchers.Main) {
                adapter.submitList(data)
            }
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    /*
    onDestroyView()를 사용하는 이유는
    1. Activity와 다르게 Fragement는 2개의 lifecycle이 존재한다.
     - Fragement Lifecycle과 Fragment View Lifecycle이 달려저서 생기는 문제인것이다.
     - 기본적으로 Fragment는 Fragment가 가지는 View보다 오래 유지된다.
    2. View Binding은 Data Binding과 다르게 lifecycle을 모륵 때문에 Binding 객체를 적절히 메모리에서 초기화 작업을 해야한다.
     */

}
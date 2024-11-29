package com.example.mydiary

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        // 해당 xml에 있는 뷰가 아니라서 따로 정의
        val recDay = findViewById<RecyclerView>(R.id.recDay)

        /*val days = mutableListOf<Int>()
        for(i in 1..31){
            days.add(i)
        }*/

        val monthManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        /*
        binding.recMonth.layoutManager = monthManager
        binding.recMonth.adapter = AdapRecMonth()
        */

        val monthListAdapter = AdapRecMonth()

        binding.recMonth.apply{
            layoutManager = monthManager
            adapter = monthListAdapter
            scrollToPosition(Int.MAX_VALUE/2)
        }


        PagerSnapHelper().attachToRecyclerView(binding.recMonth)        // 아이템 항목 단위로 스크롤 가능하도록

      /*  val dayManager = GridLayoutManager(this,7)   // 7열로 항목 배치
        recDay.layoutManager = dayManager
        recDay.adapter = AdapRecDay(days)    // (1~31)*/

    }
}
package com.example.mydiary

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mydiary.databinding.ItemMonthBinding
import java.util.Calendar
import java.util.Date


class AdapRecMonth : RecyclerView.Adapter<AdapRecMonth.ViewHolderMonth>() {
    val center = Int.MAX_VALUE/2
    class ViewHolderMonth(val binding:ItemMonthBinding) : RecyclerView.ViewHolder(binding.root)

    // inner class ViewHolderMonth(val layout: View) : RecyclerView.ViewHolder(layout)
    private var calendar = Calendar.getInstance()

    //    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMonth {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_month, parent, false)
//        return ViewHolderMonth(view)
//    }
    /*

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolderMonth(ItemMonthBinding.inflate(LayoutInflater.from(parent.context), parent, false))
*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMonth {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_month, parent, false)
        return ViewHolderMonth(ItemMonthBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolderMonth, position: Int) {
        calendar.time = Date()  // 현재 날짜
        Log.d("onBindViewHolder", "calendar.time = ${calendar.time}")
        calendar.set(Calendar.DAY_OF_MONTH, 1)  // 현재 월의 1일로 이동
        //calendar.add(Calendar.MONTH, 1200)
        calendar.add(Calendar.MONTH, position-center)
        holder.binding.tvYear.text="${calendar.get(Calendar.YEAR)}"
        holder.binding.tvMonth.text="${calendar.get(Calendar.MONTH)+1}"

        // 현재 월 저장
        val tempMonth = calendar.get(Calendar.MONTH)
        Log.d("onBindViewHolder", "tempMonth = $tempMonth")

        var dayList:MutableList<Date> = MutableList(6*7) {Date()}
        for(i in 0..5){
            for(j in 0 .. 6){
                calendar.add(Calendar.DAY_OF_MONTH, (1-calendar.get(Calendar.DAY_OF_WEEK))+j)
                dayList[i*7+j] = calendar.time
            }
            calendar.add(Calendar.WEEK_OF_MONTH, 1)
        }

        val dayListManager = GridLayoutManager(holder.binding.itemMonth.context, 7)
        val dayListAdapter = AdapRecDay(tempMonth, dayList)

        holder.binding.recDay.apply {
            layoutManager = dayListManager
            adapter = dayListAdapter
        }
    }


    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}
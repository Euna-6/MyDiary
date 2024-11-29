package com.example.mydiary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.AdapRecMonth.ViewHolderMonth
import com.example.mydiary.databinding.ItemDayBinding
import com.example.mydiary.databinding.ItemMonthBinding
import java.util.Date

class AdapRecDay(val tempMonth:Int, val dayList : MutableList<Date>) : RecyclerView.Adapter<AdapRecDay.DayView>(){

    class DayView(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return DayView(ItemDayBinding.bind(view))
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.binding.itemDay.setOnClickListener{
            Toast.makeText(holder.binding.itemDay.context, "${dayList[position]}", Toast.LENGTH_SHORT).show()
        }
        holder.binding.tvDay.text=dayList[position].date.toString()

        if (tempMonth != dayList[position].month){
            //holder.binding.tvDay.alpha = 0.4f // 투명도 조절
            holder.binding.tvDay.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int {
        return 6 * 7
    }
}
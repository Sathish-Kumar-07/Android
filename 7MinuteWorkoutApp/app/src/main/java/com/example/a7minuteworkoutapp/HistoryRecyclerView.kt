package com.example.a7minuteworkoutapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkoutapp.databinding.HistoryItemLayoutBinding
import com.google.android.material.snackbar.Snackbar



class HistoryData(val slno:Int, val dateTime:String)

class HistoryRecyclerViewHolder(val binding:HistoryItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

class HistoryRecyclerViewAdapter(private val context: Context, private var List:MutableList<HistoryData>):RecyclerView.Adapter<HistoryRecyclerViewHolder>() {

    init {
        List.sortBy { it.slno }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryRecyclerViewHolder {
        val binding = HistoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HistoryRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryRecyclerViewHolder, position: Int) {
        holder.binding.slnoTextView.text = (position+1).toString()
        holder.binding.dateTimeTextView.text = List[position].dateTime.toString()
        if(position%2==0)
            holder.binding.historyItemLayout.setBackgroundResource(R.drawable.history_item_background_2)
        else
            holder.binding.historyItemLayout.setBackgroundResource(R.drawable.history_item_background_1)
        holder.binding.historyItemLayout.setOnLongClickListener {
            val selectedItem = List[position]
            List.remove(selectedItem)
            HistoryDatabase(context).onRemoveTuple(selectedItem.slno)
            notifyDataSetChanged()
            Snackbar.make(holder.binding.root,"Deleted",Snackbar.LENGTH_LONG)
                .setAction("Undo"){
                    onUndo(selectedItem)
                }.show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return List.size
    }
    private fun onUndo(selectedItem:HistoryData){
        List.add(selectedItem)
        List.sortBy { it.slno }
        notifyDataSetChanged()
        HistoryDatabase(context).onAddTuple(selectedItem.dateTime,selectedItem.slno)
    }
}
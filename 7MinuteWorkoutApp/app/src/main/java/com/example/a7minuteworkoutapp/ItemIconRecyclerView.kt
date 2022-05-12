package com.example.a7minuteworkoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkoutapp.databinding.ItemIconExerciseRecyclerviewBinding

class ItemIconViewHolder(val binding: ItemIconExerciseRecyclerviewBinding):RecyclerView.ViewHolder(binding.root)

class ItemIconRecyclerAdapter(private val exerciseList:ArrayList<ExerciseModel>):RecyclerView.Adapter<ItemIconViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemIconViewHolder {
        val binding  = ItemIconExerciseRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemIconViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemIconViewHolder, position: Int) {
        holder.binding.textView.text = exerciseList[position].exerciseNo.toString()
        if (exerciseList[position].isSelected){
            holder.binding.textView.setBackgroundResource(R.drawable.circular_progress_bar)
        }
        if (exerciseList[position].isCompleted){
            holder.binding.textView.setBackgroundResource(R.drawable.item_circular_accent_background)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}
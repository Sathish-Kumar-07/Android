package com.example.a7minuteworkoutapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkoutapp.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    lateinit var binding:FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val list = mutableListOf<HistoryData>()
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        binding.historyItemRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.historyItemRecyclerView.adapter = HistoryRecyclerViewAdapter(requireContext(),HistoryDatabase(requireContext()).onSelectQuery().toMutableList())
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.info,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.info) {
            val dialog = AlertDialog.Builder(requireContext()).create()
            dialog.setMessage("Long Press Items to Delete History")
            dialog.show()
        }
        return super.onOptionsItemSelected(item)
    }
}
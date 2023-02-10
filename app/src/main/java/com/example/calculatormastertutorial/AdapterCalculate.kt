package com.example.calculatormastertutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterCalculate(private var dataset : ArrayList<DataCalculate>)
    : RecyclerView.Adapter<AdapterCalculate.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textMethod = view.findViewById<TextView>(R.id.txtInput)
        val textResult = view.findViewById<TextView>(R.id.txtOutput)
        val btnHapus = view.findViewById<ImageView>(R.id.ic_hapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_adapter_calculate,parent,false)
        return ViewHolder(view)
    /*return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_adapter_calculate,parent,false)
        )*/
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataset[position]
        holder.textMethod.text = dataset[position].Method
        holder.textResult.text = dataset[position].Result

        // Delete Button
        holder.btnHapus.setOnClickListener{
            dataset.removeAt(position)
            notifyItemRangeRemoved(position, dataset.size)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}
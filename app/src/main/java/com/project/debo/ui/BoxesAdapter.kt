package com.project.debo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.debo.R
import com.project.debo.databinding.BoxesItemsBinding
import com.project.debo.db.InstallmentsData

class BoxesAdapter(
    val updateHandler: (InstallmentsData) -> Unit/*, var editItem: (InstallmentsData) -> Unit*/
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var installmentsList = emptyList<InstallmentsData>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = BoxesItemsBinding.bind(view)
        val itemTitle: TextView = binding.tvName
        val itemButton: Button = binding.btAmount
        val itemPrice: TextView = binding.tvPrice
        val divider = binding.lineDivider

        fun bind(installmentsList: InstallmentsData) {
            itemTitle.text = installmentsList.name
            itemPrice.text = installmentsList.price.toString()
            itemButton.text = installmentsList.installments.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.boxes_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(installmentsList[position])

        holder.itemButton.setOnClickListener {
            updateHandler(installmentsList[position])
            notifyDataSetChanged()
        }

        if (installmentsList.size > 1) {
            holder.divider.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return installmentsList.size
    }

    fun getItemAt(position: Int): InstallmentsData {
        return installmentsList[position]
    }

    fun setData(installment: List<InstallmentsData>) {
        this.installmentsList = installment
        notifyDataSetChanged()
    }

}
package com.example.contractiontimer

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contractiontimer.database.Contraction

class ContractionAdapter(private val contractions: List<Contraction>, private val activity: MainActivity) :
    RecyclerView.Adapter<ContractionAdapter.ContractionViewHolder>() {

    inner class ContractionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNumber: TextView = itemView.findViewById(R.id.tv_contraction_number)
        val tvDuration: TextView = itemView.findViewById(R.id.tv_contraction_duration)
        val tvFrequency: TextView = itemView.findViewById(R.id.tv_contraction_frequency)
        val tvStartTime: TextView = itemView.findViewById(R.id.tv_contraction_start_time)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val contraction = contractions[position]
                    val intent = Intent(itemView.context, ContractionDetailActivity::class.java).apply {
                        putExtra("contraction_number", contraction.number)
                        putExtra("contraction_duration", contraction.duration)
                        putExtra("contraction_frequency", contraction.frequency)
                        putExtra("contraction_start", contraction.startTime)
                        putExtra("contraction_end", contraction.endTime)
                    }
                    activity.startActivityForResult(intent, MainActivity.REQUEST_CODE_DELETE)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contraction_item, parent, false)
        return ContractionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContractionViewHolder, position: Int) {
        val contraction = contractions[position]
        holder.tvNumber.text = contraction.number.toString()
        holder.tvDuration.text = contraction.duration
        holder.tvFrequency.text = contraction.frequency
        holder.tvStartTime.text = contraction.startTime
    }

    override fun getItemCount(): Int = contractions.size
}
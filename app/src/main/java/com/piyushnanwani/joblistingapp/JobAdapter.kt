package com.piyushnanwani.joblistingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobAdapter(private val jobs: List<JobItem>) :
    RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    class JobViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJobTitle: TextView = view.findViewById(R.id.tvJobTitle)
        val tvSalary: TextView = view.findViewById(R.id.tvSalary)
        val tvCompanyName: TextView = view.findViewById(R.id.tvCompanyName)
        val tvLocation: TextView = view.findViewById(R.id.tvLocation)
        val tvJobType: TextView = view.findViewById(R.id.tvJobType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_job, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = jobs[position]
        holder.tvJobTitle.text = job.title
        holder.tvSalary.text = job.salary
        holder.tvCompanyName.text = job.company
        holder.tvLocation.text = "⌖  ${job.location}"
        holder.tvJobType.text = "▣  ${job.type}"
    }

    override fun getItemCount() = jobs.size
}
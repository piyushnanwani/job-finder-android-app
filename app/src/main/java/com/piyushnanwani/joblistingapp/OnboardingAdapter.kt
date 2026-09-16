package com.piyushnanwani.joblistingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(
    private val items: List<OnboardingItem>
) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    class OnboardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.subTitle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding, parent, false)

        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: OnboardingViewHolder,
        position: Int
    ) {

        val item = items[position]

        holder.image.setImageResource(item.image)
        holder.title.text = item.title
        holder.description.text = item.description
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
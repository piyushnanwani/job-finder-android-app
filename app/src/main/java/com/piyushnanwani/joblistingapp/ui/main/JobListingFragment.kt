package com.piyushnanwani.joblistingapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.piyushnanwani.joblistingapp.JobAdapter
import com.piyushnanwani.joblistingapp.JobItemDao
import com.piyushnanwani.joblistingapp.R

/**
 * A joblisting fragment containing an optimized RecyclerView.
 */
class JobListingFragment : Fragment() {

    private val jobItemDao = JobItemDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_job_listing, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.rvJobs)
        val adapter = JobAdapter(jobItemDao.getAllJobs())
        recyclerView.adapter = adapter

        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): JobListingFragment {
            return JobListingFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
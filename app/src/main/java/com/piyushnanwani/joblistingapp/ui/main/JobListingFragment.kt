package com.piyushnanwani.joblistingapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.piyushnanwani.joblistingapp.JobAdapter
import com.piyushnanwani.joblistingapp.JobItem
import com.piyushnanwani.joblistingapp.JobItemDao
import com.piyushnanwani.joblistingapp.JobListingViewModel
import com.piyushnanwani.joblistingapp.R
import kotlinx.coroutines.launch
import androidx.fragment.app.viewModels
import com.piyushnanwani.joblistingapp.JobUiState

/**
 * A joblisting fragment containing an optimized RecyclerView.
 */
class JobListingFragment : Fragment() {

    private val jobItemDao = JobItemDao()

    private val viewModel: JobListingViewModel by viewModels()


    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_job_listing, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.rvJobs)

//        try {
//            viewLifecycleOwner.lifecycleScope.launch(){
//                jobItemDao.getJobsFlow().collect { receivedJobs ->
//                    val adapter = JobAdapter()
//                    adapter.submitList(receivedJobs)
//                    recyclerView.adapter = adapter
//                }
//
//
//            }
//        } catch (e: Exception) {
//            e.message?.let { Log.d("JobListingFragment", it) }
//        }

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.uiState.collect {
                state ->
                when(state) {
                    is JobUiState.Loading -> {
                        Log.d("JobListingFragment", "Loading...")
                    }
                    is JobUiState.Success -> {
                        val adapter = JobAdapter()
                        adapter.submitList(state.jobs)
                        recyclerView.adapter = adapter
                    }
                    is JobUiState.Error -> {
                        Log.d("JobListingFragment", "Error: ${state.message}")
                    }
                }
            }

        }


//        val adapter = JobAdapter()
//        recyclerView.adapter = adapter

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
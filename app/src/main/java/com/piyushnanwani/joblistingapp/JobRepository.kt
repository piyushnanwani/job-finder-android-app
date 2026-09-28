package com.piyushnanwani.joblistingapp;

import kotlinx.coroutines.flow.Flow

class JobRepository {
    private val dao: JobItemDao = JobItemDao()

    fun getJob(): Flow<List<JobItem>> {
        return dao.getJobsFlow()
    }

}

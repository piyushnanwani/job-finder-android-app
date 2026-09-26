package com.piyushnanwani.joblistingapp

import android.system.Os
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class JobItemDao {
    private val db = FirebaseFirestore.getInstance()
    private val jobsCollection = db.collection("jobs")

    fun getAllJobsHardCoded(): List<JobItem> {
        return listOf(
            JobItem(
                1,
                "Frontend Developer",
                "Atomxel LLP",
                "Noida, India",
                "₹ 6L - 10L",
                "Full Time"
            ),
            JobItem(
                2,
                "Android Developer",
                "TechSolutions",
                "Bengaluru, India",
                "₹ 5L - 8L",
                "Full Time"
            ),
            JobItem(
                3,
                "Backend Developer",
                "NextGen Systems",
                "Remote",
                "₹ 7L - 12L",
                "Full Time"
            ),
            JobItem(
                4,
                "UI/UX Designer",
                "Creative Minds",
                "Mumbai, India",
                "₹ 4L - 7L",
                "Part Time"
            ),
            JobItem(
                5,
                "Product Manager",
                "InnovaTech",
                "Pune, India",
                "₹ 12L - 18L",
                "Full Time"
            )
        )
    }

    suspend fun seedJobs() {
        val jobs = listOf(
            JobItem(1, "Frontend Developer", "Atomxel LLP", "Noida, India", "₹ 6L - 10L", "Full Time"),
            JobItem(2, "Android Developer", "TechSolutions", "Bengaluru, India", "₹ 5L - 8L", "Full Time"),
            JobItem(3, "Backend Developer", "NextGen Systems", "Remote", "₹ 7L - 12L", "Full Time"),
            JobItem(4, "UI/UX Designer", "Creative Minds", "Mumbai, India", "₹ 4L - 7L", "Part Time"),
            JobItem(5, "Product Manager", "InnovaTech", "Pune, India", "₹ 12L - 18L", "Full Time")
        )

        try {
            for (job in jobs) {
                // Using the ID as the document path to avoid duplicates
                jobsCollection.document(job.id.toString()).set(job).await()
            }
            Log.d("JobItemDao", "Database seeded successfully")
        } catch (e: Exception) {
            Log.e("JobItemDao", "Error seeding database", e)
        }
    }
    fun getJobsFlow(): Flow<List<JobItem>> = flow {
        println("Flow started: fetching jobs...")
        try {
            val snapshot = db.collection("jobs").get().await()
            val jobList = snapshot.toObjects<JobItem>()

            if (jobList.isEmpty()) {
                seedJobs()
                delay(1000)
                getJobsFlow().collect {
                    emit(it)
                }
            }
            emit(jobList)
        } catch (e: Exception) {
            Log.d("JobItemDao", "Error fetching jobs: ${e.message}")
        }


    }

    fun getAllJobsOG(): Flow<List<JobItem>> = callbackFlow {
        val subscription = jobsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
//                Os.close(error)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val items = snapshot.toObjects<JobItem>()
                trySend(items)
            }
            else {
                CoroutineScope(Dispatchers.IO).launch {
                    seedJobs()
                }
            }
        }
        // Keeps the flow active until it is cancelled
//        kotlinx.coroutines.channels.awaitClose { subscription.remove() }
    }

    }
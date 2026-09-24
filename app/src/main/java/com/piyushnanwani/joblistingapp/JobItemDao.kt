package com.piyushnanwani.joblistingapp

class JobItemDao {
    fun getAllJobs(): List<JobItem> {
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
}
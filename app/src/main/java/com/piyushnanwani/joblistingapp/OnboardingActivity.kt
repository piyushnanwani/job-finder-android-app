package com.piyushnanwani.joblistingapp

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var btnGetStarted: Button

    private val onboardingItems = listOf(
        OnboardingItem(
            image = R.drawable.onboarding1,
            title = "Find Your Dream Job 1",
            description = "Discover thousands of job opportunities and take the next step in your career."
        ),

        OnboardingItem(
            image = R.drawable.onboarding2,
            title = "Find Your Dream Job 2",
            description = "Discover thousands of job opportunities and take the next step in your career."
        ),
        OnboardingItem(
            image = R.drawable.onboarding3,
            title = "Find Your Dream Job 3",
            description = "Discover thousands of job opportunities and take the next step in your career."
        ),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager = findViewById(R.id.viewPager)
        dotsLayout = findViewById(R.id.dotslayout)
        btnGetStarted = findViewById(R.id.btnGetStarted)

        setupViewPager()
//        setupDots()

        btnGetStarted.setOnClickListener {
            // Handle the "Get Started" button click
            // You can navigate to the main activity or perform any other action here
        }

    }
    private fun setupViewPager() {
        val adapter = OnboardingAdapter(onboardingItems)

        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

//                updateDots(position)

            }
        })
    }
}
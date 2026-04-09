package com.example.labwork22

import android.os.Bundle
import android.os.Looper
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity : ComponentActivity() {
    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private lateinit var downloadButton: Button
    private val handler = android.os.Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = android.widget.LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            orientation = android.widget.LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
        }
        circularProgressIndicator = CircularProgressIndicator(this).apply {
            id = android.view.View.generateViewId()
            visibility = android.view.View.GONE
            indicatorSize = 52
            trackThickness = 8
            layoutParams = android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER
                bottomMargin = 32
            }
        }

        downloadButton = Button(this).apply {
            id = android.view.View.generateViewId()
            text = "Download"
            setTextColor(ContextCompat.getColor(this@MainActivity, android.R.color.white))
            setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    android.R.color.holo_blue_dark
                )
            )
            layoutParams = android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER
            }
        }
        container.addView(circularProgressIndicator)
        container.addView(downloadButton)

        setContentView(container)

        downloadButton.setOnClickListener {
            circularProgressIndicator.visibility = android.view.View.VISIBLE
        }

        handler.postDelayed({
            circularProgressIndicator.visibility = android.view.View.GONE
        }, 10000)
    }
}
package com.example.qrcodescanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            resultText.text = "Result: ${result.contents}"
        } else {
            resultText.text = "Cancelled"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanBtn = findViewById<Button>(R.id.scan_btn)
        resultText = findViewById(R.id.result_text)

        scanBtn.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Scan a QR code")
            options.setBeepEnabled(true)
            options.setOrientationLocked(true)
            options.captureActivity = CaptureAct::class.java
            barcodeLauncher.launch(options)
        }
    }
}

// Needed for camera orientation
class CaptureAct : com.journeyapps.barcodescanner.CaptureActivity()

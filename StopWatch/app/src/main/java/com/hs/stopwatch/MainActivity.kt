package com.hs.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.hs.stopwatch.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            isRunning = !isRunning

            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        binding.lapButton.setOnClickListener()
        {
            recordLapTime()
        }

        binding.resetFab.setOnClickListener()
        {
            reset()
        }


    }

    private fun pause() {
        binding.fab.setImageResource(R.drawable.ic_playarrows)
        timerTask?.cancel()
    }

    private fun start() {
        binding.fab.setImageResource(R.drawable.ic_pause)

        timerTask = timer(period = 10)
        {
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                binding.secTextView.text = "$sec"
                binding.milliTextView.text = "$milli"
            }
        }
    }

    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAP : ${lapTime / 100}.${lapTime % 100}"

        // 맨 위에 랩타임 추가
        binding.lapLayout.addView(textView, 0)
        lap++
    }

    private fun reset ()
    {
        timerTask?.cancel()

        time = 0
        isRunning = false
        binding.fab.setImageResource(R.drawable.ic_playarrows)
        binding.secTextView.text = "0"
        binding.milliTextView.text = "00"

        // 모든 랩타임 제거
        binding.lapLayout.removeAllViews()
        lap = 1
    }
}


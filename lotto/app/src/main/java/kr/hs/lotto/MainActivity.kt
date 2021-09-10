package kr.hs.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lotteryNumbers = arrayListOf(number1, number2, number3, number4, number5, number6)

        val countDownTimer = object: CountDownTimer(3000, 100){

            override fun onFinish() {
            }

            override fun onTick(p0: Long) {
                lotteryNumbers.forEach {
                    it.text = (Random.nextInt(45)+1).toString()
                }
            }
        }

        lotteryButton.setOnClickListener{
           if(lotteryButton.isAnimating) {
                lotteryButton.cancelAnimation()
                countDownTimer.cancel()
           } else {
               lotteryButton.playAnimation()
               countDownTimer.start()
           }
        }
    }
}
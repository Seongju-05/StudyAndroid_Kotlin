package kr.hs.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


            val handler = Handler(Looper.getMainLooper())
            val runnable = Runnable{
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()

            }

            // 3초 동안 가만히 있을때 자동으로 Main화면으로 넘어가게 해주는 코드
            handler.postDelayed( runnable, 3000)

        // 사용자가 클릭했을때 MainActivity로 넘어가게 하는 코드
        handler.removeCallbacks(runnable)
        animationView.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
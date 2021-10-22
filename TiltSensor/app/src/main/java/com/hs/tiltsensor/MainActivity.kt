package com.hs.tiltsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), SensorEventListener {
    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 센서 등록
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, // registerListener() 메서드로 사용할 센서를 등록한다.
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), // getDefaultSensor()로 사용할 센서 종류를 지정한다.(가속도 센서로 정함)
        SensorManager.SENSOR_DELAY_NORMAL) // 센서값을 얼마나 자주 받을것인지 지정함
    }

    // 센서값이 변경되면 호출된다.
    override fun onSensorChanged(p0: SensorEvent?) {
        TODO("Not yet implemented")
    }

    // 센서 정밀도가 변경되면 호출된다.
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    // 센서 헤제
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    // 센서값 읽기
    override fun onSensorChanged(event: SensorEvent?) {
        // 센서값이 변경되면 호출됨
        // values[0] : x축 값 : 위로 기울이면 -10 ~ 0, 아래로 기울이면 0 ~ 10
        // values[1] : y축 값 : 왼쪽으로 기울이면 -10 ~ 0, 오른쪽으로 기울이면 0 ~ 10
        // values[2] : z축 값 : 미사용
        event?.let {
            Log.d("MainActivity", "onSensorChanged: x :" +
            "${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")
        }
    }
}
package com.cvte.mindlinker.advancedplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cvte.mindlinker.advancedplan.rxjava.RxjavaActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this,RxjavaActivity::class.java))
        }
    }

    class MyThread: Thread() {
        override fun run() {
            super.run()

        }
    }
}
package com.cvte.mindlinker.advancedplan.io

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cvte.mindlinker.advancedplan.R
import java.io.*

class IoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_io)
        // output 内存 -> 文件(外存) writer
        // input 文件 -> 内存 reader

        //从内存到磁盘(外存)
       // val out = DataOutputStream(BufferedOutputStream(FileOutputStream(File("src/text.txt"))))
        //从内存角度： 读入 写出
        //out.writeInt()
        //装饰器模式

        // File -> Stream
        // 文件写出流FileOutputStream


    }



    companion object {

        //从内存 -> 文件
        private fun outputStream() {
            val outPut = DataOutputStream(BufferedOutputStream(FileOutputStream(File("text.txt"))))
            outPut.writeInt(0x01)
            outPut.writeBoolean(true)
            outPut.flush()
            outPut.close()
        }

        //从文件 -> 内存
        private fun inputStream() {
            val input = DataInputStream(BufferedInputStream(FileInputStream(File("text.txt"))))
            println("${ input.readInt()}")
            println("${ input.readBoolean()}")
            input.close()
        }
        /** 我是main入口函数 **/
        @JvmStatic
        fun main(args: Array<String>) {
            //写读，必须按照顺序
            //outputStream()
            inputStream()
        }
    }

}
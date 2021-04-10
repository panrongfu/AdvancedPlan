package com.cvte.mindlinker.advancedplan.thread

import java.util.concurrent.atomic.AtomicBoolean

class SyncTest  {
    var count = 0
    @Synchronized fun add(){
        count ++
    }
    companion object {
        val a = AtomicBoolean(true)
        class CountThread(st: SyncTest) : Thread() {
            var syncTest: SyncTest = st
            override fun run() {
                for (index in 0 until  1000) {
                    syncTest.add()
                }
            }
        }

        class CountThread2(st: SyncTest) : Thread() {
            var syncTest: SyncTest = st
            override fun run() {
                for (index in 0 until  1000) {
                    syncTest.add()
                }
            }
        }

        /** 我是main入口函数 **/
        @JvmStatic
        fun main(args: Array<String>) {
            val syncTest = SyncTest()
            val test = CountThread(syncTest)
            val test2 = CountThread2(syncTest)
            test.start()
            test2.start()
            Thread.sleep(20)
            println("count:${syncTest.count}")
        }
    }
}


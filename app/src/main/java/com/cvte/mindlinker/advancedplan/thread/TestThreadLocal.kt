package com.cvte.mindlinker.advancedplan.thread

class TestThreadLocal {
    var threadLocal = ThreadLocal<String>()

    companion object {
        class ProductThread(st: TestThreadLocal) : Thread() {
            var test: TestThreadLocal = st
            override fun run() {
                println("我是 ${Thread.currentThread().name} ---修改前 ${test.threadLocal.get()}")
                test.threadLocal.set("我的詹姆斯")
                println("我是 ${Thread.currentThread().name}-- 修改后 ${test.threadLocal.get()}")
            }
        }

        class ConsumeThread(st: TestThreadLocal) : Thread() {
            var test: TestThreadLocal = st
            override fun run() {
                println("我是 ${Thread.currentThread().name}---修改前 ${test.threadLocal.get()}")
                test.threadLocal.set("我的库兹马")
                println("我是 ${Thread.currentThread().name}-- 修改后 ${test.threadLocal.get()}")
            }
        }

        /** 我是main入口函数 **/
        @JvmStatic
        fun main(args: Array<String>) {
            val test = TestThreadLocal()
            test.threadLocal.set("大家的")
            val product = ProductThread(test)
            val consume = ConsumeThread(test)
            consume.start()
            product.start()
        }
    }

    inner class TestThread: Thread() {

    }

}
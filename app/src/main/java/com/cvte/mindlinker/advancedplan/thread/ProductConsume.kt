package com.cvte.mindlinker.advancedplan.thread


class ProductConsume {
    var name: String = ""
    var id: Int = 0
    val lock =  Object()
    var flag = false
    fun product(){
        synchronized(lock) {
            if (!flag) {
                id ++
                println("${Thread.currentThread().name} 生产了一个苹果 id: ${id}" )
                flag = true
                lock.notify()
                try {
                    lock.wait()
                } catch (e: Exception) {
                }
            }

        }
    }

     fun consume() {
         synchronized(lock) {
             if (flag) {
                 flag = false
                 println("${Thread.currentThread().name} 消费了一个 id: ${id}" )
                 lock.notify()
                 try {
                     lock.wait()
                 } catch (e: Exception) {
                 }
             }
         }
    }

    companion object {
        class ProductThread(st: ProductConsume) : Thread() {
            var syncTest: ProductConsume = st
            override fun run() {
                for (index in 0 until  20) {
                    syncTest.product()
                }
            }
        }

        class ConsumeThread(st: ProductConsume) : Thread() {
            var syncTest: ProductConsume = st
            override fun run() {
                for (index in 0 until  20) {
                    syncTest.consume()
                }
            }
        }

        /** 我是main入口函数 **/
        @JvmStatic
        fun main(args: Array<String>) {
            val syncTest = ProductConsume()
            val product = ProductThread(syncTest)
            val consume = ConsumeThread(syncTest)
            consume.start()
            product.start()
        }
    }
}
package com.cvte.mindlinker.advancedplan.rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cvte.mindlinker.advancedplan.R
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava.*
import java.util.concurrent.TimeUnit

class RxjavaActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)
        //transformer
        Observable.just("")
            .compose(transformer())
        //防抖动
        button.clicks()
            .throttleFirst(2000, TimeUnit.MILLISECONDS)
            .subscribe {
                // TODO: 2021/4/10 点击视觉
                Toast.makeText(this, "防抖测试", Toast.LENGTH_LONG).show()
            }
        //doOnNext运用
        Observable.just(true)
            .compose(transformer())
            .subscribeOn(Schedulers.io())
            .doOnNext {
                // TODO: 2021/4/10 可以更新UI
            }
            .observeOn(Schedulers.io())
            .flatMap { _ ->
                // TODO: 2021/4/10 继续进行网络请求
                Observable.just(true)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                // TODO: 2021/4/10 更新UI
            }
            .map(Function<Boolean, Boolean> {
                true
            })

            .flatMap {
                // TODO: 2021/4/10 继续请求API
                Observable.just(true)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                // TODO: 2021/4/10 最终更新UI
            }
        Observable.create<Boolean> {
            it.onNext(true)
        }
            .subscribe {

            }
        Observable.create<Boolean>(object : ObservableOnSubscribe<Boolean> {
            override fun subscribe(emitter: ObservableEmitter<Boolean>) {
                TODO("Not yet implemented")
            }
        })
            .map(Function<Boolean,Boolean> {
                true
            })
            .subscribe(object : Observer<Boolean> {
                override fun onComplete() {
                    TODO("Not yet implemented")
                }

                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onNext(t: Boolean) {
                    TODO("Not yet implemented")
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })


    }
}
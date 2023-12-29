package com.example.myrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doOnTextChanged
import com.example.myrxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var mDisposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        foo()
    }
    private fun foo(){
        val list = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")

        list.toObservable() // extension function for Iterables
            .filter { it.length >= 5 }
            .subscribeBy(  // named arguments for lambda Subscribers
                onNext = { println(it) },
                onError =  { it.printStackTrace() },
                onComplete = { println("Done!") }
            )
//       val observable=Observable.interval(500,TimeUnit.MILLISECONDS).take(3)
//        val subject= AsyncSubject.create<Long>()
//        observable.subscribe(subject)
//
//        subject.subscribe(
//            {
//                Log.d(TAG, "next: $it")
//            },
//            {
//                Log.d(TAG, "error ${it.message}")
//            }
//        )
//        val observable = Observable.interval(1,TimeUnit.SECONDS).take(10).publish()
//        observable.connect()
//        observable.subscribe(
//            {t->
//                Log.i(TAG, "first observer: $t")
//            },
//            {e->
//                Log.d(TAG, "error: ${e.message}")
//            }
//        )
//        Thread.sleep(3500)
//        observable.subscribe(
//            {t->
//                Log.i(TAG, "second observer: $t")
//            },
//            {e->
//                Log.d(TAG, "error: ${e.message}")
//            }
//        )

//        observable.toFlowable(BackpressureStrategy.LATEST).onBackpressureDrop().observeOn(Schedulers.io(),false,5).subscribe(
//            {t->
//                Log.d(TAG, "next: $t")
//            },
//            {e->
//                Log.d(TAG, "errror: ${e.message}")
//            },
//            {
//                Log.d(TAG, "compelete")
//            }
//        )
//        val maybe= Maybe.create<String> { emitter->
//            binding.editTxt.doOnTextChanged { text, start, before, count ->
//                when(text.toString()){
//                    "hello" -> emitter.onSuccess("hi,Welcome")
//                    "no" -> emitter.onComplete()
//                }
//            }
//        }
//        maybe.subscribe(object :MaybeObserver<String>{
//            override fun onSubscribe(d: Disposable) {
//                Log.d(TAG, "onSubscribe: ")
//            }
//
//            override fun onError(e: Throwable) {
//                Log.d(TAG, "onError: ${e?.message}")
//            }
//
//            override fun onComplete() {
//                Log.d(TAG, "onComplete: ")
//            }
//
//            override fun onSuccess(t: String) {
//                Log.d(TAG, "onSuccess: $t")
//            }
//
//        })
//        val completable=Completable.create { emitter->
//            binding.editTxt.doOnTextChanged { text, start, before, count ->
//                if (text.toString() == "000") {
//                    emitter.onComplete()
//                }
//            }
//        }
//        completable.subscribe {
//            object : CompletableObserver {
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onComplete() {
//                    Log.d(TAG, "onComplete")
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.d(TAG, "onError")
//                }
//
//            }
//        }
        val single=Single.just(5)
        single.subscribe(object : SingleObserver<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: ${e.message}")
            }

            override fun onSuccess(t: Int) {
                Log.d(TAG, "onSuccess: $t")
            }

        })
//        val list= listOf(1,2,3,4,5,6,7,8,9,6,7,2,5,8,10,11,12)

//        val observable=Observable.range(20,29).repeat(2)
//        val observable=Observable.interval(1,TimeUnit.SECONDS).take(5,TimeUnit.SECONDS)
//        val observable=Observable.range(1,100).takeLast(20)
//        val observable=Observable.timer(10,TimeUnit.SECONDS)
//        val observable=Observable.fromIterable(list).distinct()
//        val observable=Observable.fromIterable(list).buffer(3)
//        val observable=Observable.range(2,20).map { it * 2 }
        //val someObservable=Observable.interval(500,TimeUnit.MILLISECONDS).take(10).map { it * 10 }
//        val observable=Observable.interval(1,TimeUnit.SECONDS).take(10).map { it*2 }.mergeWith(someObservable)

//        val observable=Observable.range(1,1000)
//        compositeDisposable.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{ t->
//            Log.d(TAG, "onNext:  $t -${Thread.currentThread().name}")
//        })


//        val observer=object :Observer<Int>{
//            override fun onSubscribe(d: Disposable) {
//                Log.d(TAG, "onSubscribe: ")
//            }
//
//            override fun onError(e: Throwable) {
//                Log.d(TAG, "onError: ")
//            }
//
//            override fun onComplete() {
//                Log.d(TAG, "onComplete: ")
//            }
//
//            override fun onNext(t: Int) {
//                Log.d(TAG, "onNext: $t")
//            }
//
//        }
//        happy.subscribe(observer)

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

//    private fun foo() {
//        var foo=5
//        Log.d(TAG, foo.toString())
//        foo=10
//        Log.d(TAG, foo.toString())
//        foo=20
//        Log.d(TAG, foo.toString())
//        foo=40
//        Log.d(TAG, foo.toString())
//        foo=80
//        Log.d(TAG, foo.toString())
//        foo=160
//        Log.d(TAG, foo.toString())
//    }

    companion object {
        const val TAG = "Tamer"
    }
}
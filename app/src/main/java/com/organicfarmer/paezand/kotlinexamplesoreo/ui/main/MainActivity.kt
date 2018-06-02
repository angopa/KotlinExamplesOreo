package com.organicfarmer.paezand.kotlinexamplesoreo.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.organicfarmer.paezand.myapplication.R
import com.organicfarmer.paezand.kotlinexamplesoreo.ui.calculator.CalculatorActivity

private val TAG = "MainActivity"

class MainActivity : MvpActivity<IMainView, IMainPresenter>(), IMainView {
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "======= onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showCalculatorScreen()
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    private fun initUi() {
        button = findViewById(R.id.button_calculator)

        button?.setOnClickListener {
            presenter.onCalculatorButtonTapped()
        }
    }

    override fun showCalculatorScreen() {
        startActivity(CalculatorActivity.newIntent(this))
    }

    override fun onStart() {
        Log.d(TAG, "======= onStart")
        super.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.d(TAG, "======= onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
//        textView?.text = savedInstanceState?.getString(TEXT_CONTENTS, "")
    }

    override fun onResume() {
        Log.d(TAG, "======= onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "======= onPause")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        Log.d(TAG, "======= onSaveInstanceState")
//        outState?.putString(TEXT_CONTENTS, textView?.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Log.d(TAG, "======= onStop")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "======= onRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "======= onDestroy")
        super.onDestroy()
    }

}
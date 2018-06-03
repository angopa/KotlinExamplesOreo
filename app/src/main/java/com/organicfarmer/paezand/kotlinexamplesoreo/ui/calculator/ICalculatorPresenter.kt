package com.organicfarmer.paezand.kotlinexamplesoreo.ui.calculator

import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpPresenter

interface ICalculatorPresenter : MvpPresenter<ICalculatorView> {
    fun performOperation(value: Double, operation: String)
    fun onButtonNegTapped(toString: String)
    fun saveInstanceState(outState: Bundle?)
    fun restoreInstanceState(savedInstanceState: Bundle?)
    fun buttonNumberTapped(text: CharSequence?)
}
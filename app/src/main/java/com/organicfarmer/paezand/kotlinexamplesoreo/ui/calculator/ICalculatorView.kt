package com.organicfarmer.paezand.kotlinexamplesoreo.ui.calculator

import com.hannesdorfmann.mosby.mvp.MvpView

interface ICalculatorView : MvpView {
    fun updateOperationValue(operation: String)
    fun updateResultValue(result: String)
    fun updateNewNumberValue(newNumber: String)
    fun appendNewNumberValue(toString: String)
}
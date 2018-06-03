package com.organicfarmer.paezand.kotlinexamplesoreo.ui.calculator

import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter

private const val OPERAND_ONE = "CalculatorActivity.OPERAND_ONE"
private const val STATE_PENDING_OPERATION = "CalculatorActivity.OPERATION"

class CalculatorPresenter : MvpBasePresenter<ICalculatorView>(), ICalculatorPresenter {

    //Variables to hold the operands and the type of calculation
    private var operand1: Double? = null
    private var pendingOperation = ""

    override fun saveInstanceState(outState: Bundle?) {
        outState?.putString(STATE_PENDING_OPERATION, pendingOperation)
        operand1?.let { outState?.putDouble(OPERAND_ONE, it) }
    }

    override fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (!isViewAttached) {
            return
        }
        pendingOperation = savedInstanceState?.getString(STATE_PENDING_OPERATION, "").toString()
        operand1 = savedInstanceState?.getDouble(OPERAND_ONE, 0.0)
        view?.updateResultValue(operand1.toString())
        view?.updateOperationValue(pendingOperation)
    }

    override fun buttonNumberTapped(text: CharSequence?) {
        if (isViewAttached) {
            view?.appendNewNumberValue(text.toString())
        }
    }

    override fun performOperation(value: Double, operation: String) {
        if (!isViewAttached) {
            return
        }
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = operation
            }

            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        view?.updateOperationValue(operation)
        view?.updateResultValue(operand1.toString())
        view?.updateNewNumberValue("")
        pendingOperation = operation
    }

    override fun onButtonNegTapped(value: String) {
        if (!isViewAttached) {
            return
        }
        var newNumber = ""
        if (!value.isEmpty()) {
            try {
                var doubleValue = value.toDouble()
                doubleValue *= -1
                newNumber = doubleValue.toString()
            } catch (e : NumberFormatException) {
                println(e.message)
            }
        }
        view?.updateNewNumberValue(newNumber)
    }

}
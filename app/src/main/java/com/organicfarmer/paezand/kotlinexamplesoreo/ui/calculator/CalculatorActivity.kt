package com.organicfarmer.paezand.kotlinexamplesoreo.ui.calculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.organicfarmer.paezand.kotlinexamplesoreo.R
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : MvpActivity<ICalculatorView, ICalculatorPresenter>(), ICalculatorView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CalculatorActivity::class.java)
        }
    }

    override fun createPresenter(): ICalculatorPresenter {
        return CalculatorPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val listener = View.OnClickListener { v ->
            presenter.buttonNumberTapped((v as Button).text)
        }

        buttonCero.setOnClickListener(listener)
        buttonOne.setOnClickListener(listener)
        buttonTwo.setOnClickListener(listener)
        buttonThree.setOnClickListener(listener)
        buttonFour.setOnClickListener(listener)
        buttonFive.setOnClickListener(listener)
        buttonSix.setOnClickListener(listener)
        buttonSeven.setOnClickListener(listener)
        buttonEight.setOnClickListener(listener)
        buttonNine.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { view ->
            try {
                presenter.performOperation(newNumber.text.toString().toDouble(),
                                            (view as Button).text.toString())
            } catch(e: NumberFormatException) {
                newNumber.setText("")
            }
        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)

        buttonNeg.setOnClickListener({ view ->
            presenter.onButtonNegTapped(newNumber.text.toString())
        })
    }

    override fun updateOperationValue(operation: String) {
        this.operation?.text = operation
    }

    override fun updateResultValue(result: String) {
        this.result?.setText(result)
    }

    override fun appendNewNumberValue(newNumber: String) {
        this.newNumber?.append(newNumber)
    }

    override fun updateNewNumberValue(newNumber: String) {
        this.newNumber?.setText(newNumber)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        presenter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.restoreInstanceState(savedInstanceState)
    }
}
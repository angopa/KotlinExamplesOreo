package com.organicfarmer.paezand.kotlinexamplesoreo.ui.calculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.organicfarmer.paezand.myapplication.R
import kotlinx.android.synthetic.main.activity_calculator.*

private const val OPERAND_ONE = "CalculatorActivity.OPERAND_ONE"
private const val STATE_PENDING_OPERATION = "CalculatorActivity.OPERATION"

class CalculatorActivity : MvpActivity<ICalculatorView, ICalculatorPresenter>(), ICalculatorView {
//    private lateinit var result: EditText
//    private lateinit var newNumber: EditText
//    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CalculatorActivity::class.java)
        }
    }

    //Variables to hold the operands and the type of calculation
    private var operand1: Double? = null
    private var pendingOperation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

//        result = findViewById(R.id.result)
//        newNumber = findViewById(R.id.newNumber)
//
//        //Data input buttons
//        val buttonCero: Button = findViewById(R.id.buttonCero)
//        val buttonOne: Button = findViewById(R.id.buttonOne)
//        val buttonTwo: Button = findViewById(R.id.buttonTwo)
//        val buttonThree: Button = findViewById(R.id.buttonThree)
//        val buttonFour: Button = findViewById(R.id.buttonFour)
//        val buttonFive: Button = findViewById(R.id.buttonFive)
//        val buttonSix: Button = findViewById(R.id.buttonSix)
//        val buttonSeven: Button = findViewById(R.id.buttonSeven)
//        val buttonEight: Button = findViewById(R.id.buttonEight)
//        val buttonNine: Button = findViewById(R.id.buttonNine)
//        val buttonDot: Button = findViewById(R.id.buttonDot)
//
//        //Operation buttons
//        val buttonEquals: Button = findViewById(R.id.buttonEquals)
//        val buttonDivide: Button = findViewById(R.id.buttonDivide)
//        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
//        val buttonMinus: Button = findViewById(R.id.buttonMinus)
//        val buttonPlus: Button = findViewById(R.id.buttonPlus)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
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
            val operation = (view as Button).text.toString()
            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, operation)
            } catch(e: NumberFormatException) {
                newNumber.setText("")
            }
            pendingOperation = operation
            operation_text_view.text = pendingOperation
        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
    }

    private fun performOperation(value: Double, operation: String) {
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
        result.setText(operand1.toString())
        newNumber.setText("")
        operation_text_view.text = operation
    }

    override fun createPresenter(): ICalculatorPresenter {
        return CalculatorPresenter()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(STATE_PENDING_OPERATION, pendingOperation)
        operand1?.let { outState?.putDouble(OPERAND_ONE, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        pendingOperation = savedInstanceState?.getString(STATE_PENDING_OPERATION, "").toString()
        operand1 = savedInstanceState?.getDouble(OPERAND_ONE, 0.0)
        result.setText(operand1.toString())
        operation_text_view.text = pendingOperation
    }
}
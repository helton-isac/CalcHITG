package br.com.hitg.simplecalculator.calculator

import br.com.hitg.simplecalculator.R

/**
 * Created by Helton on 20/02/2018.
 *
 * Calculator Machine
 */
class Calculator(val digits: Int) {
    lateinit var displayNumber: String
    private var isOperatorSignalDisplayed: Boolean = false
    var tempMemory: Double = 0.0
    var currentOperation: Operations? = null

    val userMemory: CalculatorUserMemory = CalculatorUserMemory()

    val MAX_DIGITS_ALLOWED: Int = 16

    val ERROR_MAX_DIGITS_EXCEEDED: String = "ERROR_MAX_DIGITS_EXCEEDED"

    init {
        if (digits > MAX_DIGITS_ALLOWED) {
            throw Exception(ERROR_MAX_DIGITS_EXCEEDED)
        }
        initializeCalculator()
        initializeUserMemory()
    }

    private fun initializeUserMemory() {
        userMemory.mrc()
    }

    fun initializeCalculator() {
        displayNumber = "0"
        isOperatorSignalDisplayed = false;
        tempMemory = 0.0
    }

    fun typeNumber(number: Int) {
        if (displayNumber == "0" || isOperatorSignalDisplayed) {
            displayNumber = number.toString()
        } else {
            displayNumber += number.toString()
        }
        isOperatorSignalDisplayed = false
    }

    fun add() {
        performOperationClick(Operations.ADDITION, "+")
    }

    fun subtract() {
        performOperationClick(Operations.SUBTRACTION, "-")
    }

    fun divide() {
        performOperationClick(Operations.DIVISION, "/")
    }

    fun multiply() {
        performOperationClick(Operations.MULTIPLICATION, "*")
    }

    fun performOperationClick(operation: Operations, operatorSignal: String) {
        if (!isOperatorSignalDisplayed) {
            this.updateTempMemory()
        } else {
            backspace()
        }
        currentOperation = operation
        displayNumber = tempMemory.toString() + operatorSignal
        isOperatorSignalDisplayed = true;
    }

    fun backspace() {
        if (displayNumber.length > 0) displayNumber = displayNumber.substring(0, displayNumber.length - 1)
        if (displayNumber == "") displayNumber = "0"

    }

    private fun updateTempMemory() {
        when (currentOperation) {
            Operations.ADDITION ->
                tempMemory += displayNumber.toDouble()
            Operations.SUBTRACTION ->
                tempMemory -= displayNumber.toDouble()
            Operations.DIVISION ->
                tempMemory /= displayNumber.toDouble()
            Operations.MULTIPLICATION ->
                tempMemory *= displayNumber.toDouble()
            else -> tempMemory = displayNumber.toDouble()
        }
    }


    fun equals() {
        this.updateTempMemory()
        displayNumber = tempMemory.toString()
    }

    fun dot() {
        if (!isOperatorSignalDisplayed) {
            this.updateTempMemory()
        } else {
            backspace()
        }
        displayNumber = tempMemory.toString() + "."
    }

    fun isDotVisible(): Boolean = displayNumber.contains(".")

    fun memoryAdd() {
        if (isOperatorSignalDisplayed) {
            this.userMemory.mPlus(displayNumber.substring(0, displayNumber.length - 1).toDouble())
        } else {
            this.userMemory.mPlus(displayNumber.toDouble())
        }
    }

    fun memorySubtract() {
        if (isOperatorSignalDisplayed) {
            this.userMemory.mSubtract(displayNumber.substring(0, displayNumber.length - 1).toDouble())
        } else {
            this.userMemory.mSubtract(displayNumber.toDouble())
        }
    }

    fun memoryResultAndClean() {
        displayNumber = this.userMemory.mrc().toString()
    }

    fun squareRoot() {
        if (isOperatorSignalDisplayed) {
            backspace()
            isOperatorSignalDisplayed = false;
        }
        displayNumber = Math.sqrt(displayNumber.toDouble()).toString()

    }

    fun percent() {
        if (isOperatorSignalDisplayed) {
            backspace()
            isOperatorSignalDisplayed = false;
        }
        displayNumber = (displayNumber.toDouble() * tempMemory / 100).toString()
    }

    fun ce() {
        displayNumber = "0"
    }
}
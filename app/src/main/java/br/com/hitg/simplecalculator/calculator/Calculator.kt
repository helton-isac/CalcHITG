package br.com.hitg.simplecalculator.calculator

import java.math.BigDecimal

/**
 * Created by Helton on 20/02/2018.
 *
 * Class responsible by managing the memory and calculations.
 */
class Calculator(val digits: Int) {

    /**
     * Display Number.
     *
     * The typed number and results must be saved in this variable.
     */
    lateinit var displayNumber: String
        private set

    /**
     * When an operation is initialized, must clean the current display number.
     */
    private var cleanDisplayOnNextInteraction: Boolean = false

    /**
     * Current Total of the current calculus.
     */
    private var currentTotal: BigDecimal = BigDecimal(0)

    /**
     * Current Operation.
     */
    var currentOperation: Operations = Operations.NONE
        private set

    /**
     * User Memory.
     *
     * Used by the user to manage a calc memory.
     */
    private val userMemory: CalculatorUserMemory = CalculatorUserMemory()

    /**
     * Max digits allowed.
     */
    private val MAX_DIGITS_ALLOWED: Int = 16

    /**
     * Max digits Exceeded message.
     */
    private val ERROR_MAX_DIGITS_EXCEEDED: String = "ERROR_MAX_DIGITS_EXCEEDED"

    init {
        if (digits > MAX_DIGITS_ALLOWED) {
            throw Exception(ERROR_MAX_DIGITS_EXCEEDED)
        }
        initializeCalculator()
        userMemory.mrc()
    }

    fun initializeCalculator() {
        applyResult(0.0)
        currentOperation = Operations.NONE
        cleanDisplayOnNextInteraction = false
        currentTotal = BigDecimal(0)
    }

    fun applyResult(value: Double) {
        displayNumber = if (value % 1 == 0.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    fun typeNumber(number: Int) {
        if (displayNumber == "0" || cleanDisplayOnNextInteraction) {
            displayNumber = number.toString()
        } else {
            displayNumber += number.toString()
        }
        cleanDisplayOnNextInteraction = false
    }

    fun add() {
        performOperation(Operations.ADDITION)
    }

    fun subtract() {
        performOperation(Operations.SUBTRACTION)
    }

    fun divide() {
        performOperation(Operations.DIVISION)
    }

    fun multiply() {
        performOperation(Operations.MULTIPLICATION)
    }

    fun performOperation(operation: Operations) {
        this.updateTempMemory()
        currentOperation = operation
        applyResult(currentTotal.toDouble())
        cleanDisplayOnNextInteraction = true
    }

    private fun updateTempMemory() {
        if (!cleanDisplayOnNextInteraction || currentOperation == Operations.NONE) {
            currentTotal = when (currentOperation) {
                Operations.ADDITION ->
                    currentTotal.add(BigDecimal.valueOf(displayNumber.toDouble()))
                Operations.SUBTRACTION ->
                    currentTotal.subtract(BigDecimal.valueOf(displayNumber.toDouble()))
                Operations.DIVISION ->
                    currentTotal.divide(BigDecimal.valueOf(displayNumber.toDouble()))
                Operations.MULTIPLICATION ->
                    currentTotal.multiply(BigDecimal.valueOf(displayNumber.toDouble()))
                Operations.NONE ->
                    BigDecimal.valueOf(displayNumber.toDouble())
            }
        }
    }

    fun backspace() {
        if (displayNumber.length > 0) {
            displayNumber = displayNumber.substring(0, displayNumber.length - 1)
        }
        if (displayNumber == "") {
            displayNumber = "0"
        }
    }


    fun equals() {
        this.updateTempMemory()
        applyResult(currentTotal.toDouble())
        currentTotal = BigDecimal(0)
        currentOperation = Operations.NONE
        cleanDisplayOnNextInteraction = true
    }

    fun typeDot() {
        if (!isDotVisible()) {
            displayNumber = displayNumber + "."
        } else if (cleanDisplayOnNextInteraction) {
            displayNumber = "0."
            cleanDisplayOnNextInteraction = false;
        }
    }

    fun isDotVisible(): Boolean = displayNumber.contains(".")

    fun memoryAdd() {
        this.userMemory.mPlus(displayNumber.toDouble())
    }

    fun memorySubtract() {
        this.userMemory.mSubtract(displayNumber.toDouble())
    }

    fun memoryResultAndClean() {
        applyResult(this.userMemory.mrc().toDouble())
    }

    fun squareRoot() {
        applyResult(Math.sqrt(displayNumber.toDouble()))
    }

    fun percent() {
        applyResult(
                currentTotal.multiply(
                        BigDecimal.valueOf(
                                displayNumber.toDouble()))
                        .divide(
                                BigDecimal.valueOf(100)).toDouble())
    }

    fun ce() {
        initializeCalculator()
    }

    fun isMemoryInUse(): Boolean {
        return userMemory.isMemoryInUse
    }
}
package br.com.hitg.simplecalculator.calculator

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

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

    private var lastOperation: Operations = Operations.NONE
    private var lastInput: BigDecimal = BigDecimal("0")

    init {
        if (digits > MAX_DIGITS_ALLOWED) {
            throw Exception(ERROR_MAX_DIGITS_EXCEEDED)
        }
        initializeCalculator()
        userMemory.mrc()
    }

    fun initializeCalculator() {
        currentTotal = BigDecimal(0)
        applyResult(currentTotal)
        currentOperation = Operations.NONE
        cleanDisplayOnNextInteraction = false
    }

    fun applyResult(value: BigDecimal) {
        val df = DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
        df.maximumFractionDigits = 340
        displayNumber = df.format(value)
    }

    private fun isIntegerValue(bd: BigDecimal): Boolean {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0
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
        applyResult(currentTotal)
        cleanDisplayOnNextInteraction = true
    }

    private fun updateTempMemory() {
        if (!cleanDisplayOnNextInteraction ||
                currentOperation == Operations.NONE ||
                currentOperation == lastOperation) {
            currentTotal = calculate(currentOperation, currentTotal, BigDecimal(displayNumber))
        }
    }

    private fun calculate(operation: Operations, value1: BigDecimal, value2: BigDecimal): BigDecimal {
        return when (operation) {
            Operations.ADDITION -> value1.add(value2)
            Operations.SUBTRACTION -> value1.subtract(value2)
            Operations.DIVISION -> value1.divide(value2, 8, RoundingMode.HALF_UP)
            Operations.MULTIPLICATION -> value1.multiply(value2)
            Operations.NONE -> value2
        }
    }

    fun backspace() {
        if (displayNumber.length > 0 && !displayNumber.equals("0")) {
            displayNumber = displayNumber.substring(0, displayNumber.length - 1)
            if (displayNumber == "") {
                displayNumber = "0"
            }
        } else {
            displayNumber = "0"
            currentOperation = Operations.NONE
            lastOperation = Operations.NONE
        }

    }


    fun equals() {
        if (currentOperation != Operations.NONE) {
            lastOperation = currentOperation
            if (lastOperation == Operations.DIVISION) {
                lastInput = currentTotal
            } else {
                lastInput = BigDecimal(displayNumber)
            }
            this.updateTempMemory()
        } else {
            if (lastOperation != Operations.NONE) {
                currentTotal = calculate(lastOperation, BigDecimal(displayNumber), lastInput)
            } else {
                currentTotal = BigDecimal(displayNumber)
            }
        }
        applyResult(currentTotal)
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
        equals()
        userMemory.mPlus(BigDecimal(displayNumber))
    }

    fun memorySubtract() {
        equals()
        userMemory.mSubtract(BigDecimal(displayNumber))
    }

    fun memoryResultAndClean() {
        if (userMemory.isMemoryInUse) {
            if (currentOperation != Operations.NONE) {
                applyResult(this.userMemory.mr())
            } else {
                applyResult(this.userMemory.mrc())
            }

        }
    }

    fun squareRoot() {
        applyResult(BigDecimal(Math.sqrt(displayNumber.toDouble()).toString()))
    }

    fun percent() {
        applyResult(currentTotal.multiply(BigDecimal(displayNumber)).divide(BigDecimal("100")))
    }

    fun ce() {
        initializeCalculator()
    }

    fun isMemoryInUse(): Boolean {
        return userMemory.isMemoryInUse
    }
}
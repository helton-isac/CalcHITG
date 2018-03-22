package br.com.hitg.simplecalculator.calculator

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Created by Helton on 20/02/2018.
 *
 * Class responsible by managing the memory and calculations.
 */
class Calculator {

    /**
     * Display Number.
     *
     * The typed number and results must be saved in this variable.
     */
    var displayNumber: CalculatorDisplay = CalculatorDisplay(8)

    /**
     * When an operation is initialized, must clean the current display number.
     */
    private var cleanDisplayOnNextInteraction: Boolean = false

    /**
     * Current Total of the current calculus.
     */
    private var currentTotal: BigDecimal = BigDecimal("0")

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
     * Save the last Operation to be used in Equals Repeated Operations.
     */
    private var lastOperation: Operations = Operations.NONE

    /**
     * Save the last Input to be used in Equals Repeated Operations.
     */
    private var lastInput: BigDecimal = BigDecimal("0")

    /**
     * Initialize Class
     */
    init {
        ce()
        userMemory.mrc()
    }

    private fun applyResult(value: BigDecimal) {
        val df = DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
        df.maximumFractionDigits = 340
        displayNumber.setValue(df.format(value))
        cleanDisplayOnNextInteraction = true
    }

    fun typeNumber(number: Char) {
        if (displayNumber.appendNumber(number, cleanDisplayOnNextInteraction)) {
            cleanDisplayOnNextInteraction = false
        }
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

    private fun performOperation(operation: Operations) {
        this.updateTempMemory()
        currentOperation = operation
        applyResult(currentTotal)
        cleanDisplayOnNextInteraction = true
    }

    private fun updateTempMemory() {
        if (!cleanDisplayOnNextInteraction ||
                currentOperation == Operations.NONE ||
                currentOperation == lastOperation) {
            currentTotal = calculate(currentOperation, currentTotal, displayNumber.toBigDecimal())
        }
    }

    private fun calculate(operation: Operations,
                          value1: BigDecimal,
                          value2: BigDecimal): BigDecimal {
        return when (operation) {
            Operations.ADDITION       -> MathEngine.add(value1, value2)
            Operations.SUBTRACTION    -> MathEngine.subtract(value1, value2)
            Operations.DIVISION       -> MathEngine.divide(value1, value2)
            Operations.MULTIPLICATION -> MathEngine.multiply(value1, value2)
            Operations.NONE           -> value2
        }
    }

    fun backspace() {
        if (!displayNumber.removeLast()) {
            currentOperation = Operations.NONE
            lastOperation = Operations.NONE
        }
    }


    fun equals() {
        if (currentOperation != Operations.NONE) {
            lastOperation = currentOperation
            lastInput = displayNumber.toBigDecimal()
            this.updateTempMemory()
        } else {
            currentTotal = if (lastOperation != Operations.NONE) {
                calculate(lastOperation, displayNumber.toBigDecimal(), lastInput)
            } else {
                displayNumber.toBigDecimal()
            }
        }
        applyResult(currentTotal)
        currentTotal = BigDecimal(0)
        currentOperation = Operations.NONE
        cleanDisplayOnNextInteraction = true
    }

    fun typeDot() {
        if (displayNumber.appendDecimalSeparator(cleanDisplayOnNextInteraction)) {
            cleanDisplayOnNextInteraction = false
        }

    }

    fun memoryAdd() {
        equals()
        userMemory.mPlus(displayNumber.toBigDecimal())
    }

    fun memorySubtract() {
        equals()
        userMemory.mSubtract(displayNumber.toBigDecimal())
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
        displayNumber.setValue(Math.sqrt(displayNumber.toBigDecimal().toDouble()).toString())
        applyResult(displayNumber.toBigDecimal())
    }

    fun percent() {
        applyResult(currentTotal.multiply(displayNumber.toBigDecimal()).divide(BigDecimal("100")))
    }

    fun ce() {
        currentTotal = BigDecimal(0)
        applyResult(currentTotal)
        currentOperation = Operations.NONE
        cleanDisplayOnNextInteraction = false
    }

    fun isMemoryInUse(): Boolean {
        return userMemory.isMemoryInUse
    }
}
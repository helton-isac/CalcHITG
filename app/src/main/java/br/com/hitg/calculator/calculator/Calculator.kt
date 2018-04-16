package br.com.hitg.calculator.calculator

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

    /**
     * Apply Result on Display.
     *
     * @param value Value to apply.
     */
    private fun applyResult(value: BigDecimal) {
        val df = DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
        df.maximumFractionDigits = 340
        displayNumber.setValue(df.format(value))
        cleanDisplayOnNextInteraction = true
    }

    /**
     * Handle Typed Number.
     *
     * @param number Typed Number.
     */
    fun typeNumber(number: Char) {
        if (displayNumber.appendNumber(number, cleanDisplayOnNextInteraction)) {
            cleanDisplayOnNextInteraction = false
        }
    }

    /**
     * ADDITION operation;
     */
    fun add() {
        performOperation(Operations.ADDITION)
    }

    /**
     * SUBTRACTION operation;
     */
    fun subtract() {
        performOperation(Operations.SUBTRACTION)
    }

    /**
     * DIVISION operation;
     */
    fun divide() {
        performOperation(Operations.DIVISION)
    }

    /**
     * MULTIPLICATION operation;
     */
    fun multiply() {
        performOperation(Operations.MULTIPLICATION)
    }

    /**
     * Execute a give operation.
     *
     * @param operation Operation to execute.
     */
    private fun performOperation(operation: Operations) {
        this.updateTempMemory()
        currentOperation = operation
        applyResult(currentTotal)
        cleanDisplayOnNextInteraction = true
    }

    /**
     * Update the temp memory Total.
     */
    private fun updateTempMemory() {
        if (!cleanDisplayOnNextInteraction ||
                currentOperation == Operations.NONE ||
                currentOperation == lastOperation) {
            currentTotal = calculate(currentOperation, currentTotal, displayNumber.toBigDecimal())
        }
    }

    /**
     * Calculate.
     *
     * @param operation Operation
     * @param value1 Value 1 of the operation
     * @param value2 Value 2 of the operation
     */
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

    /**
     * Remove the last character.
     */
    fun removeLast() {
        if (!displayNumber.removeLast()) {
            currentOperation = Operations.NONE
            lastOperation = Operations.NONE
        }
    }

    /**
     * Performs the Equals operation.
     */
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

    /**
     * Handle typed dot.
     */
    fun typeDot() {
        if (displayNumber.appendDecimalSeparator(cleanDisplayOnNextInteraction)) {
            cleanDisplayOnNextInteraction = false
        }

    }

    /**
     * Add the current display to the memory.
     */
    fun memoryAdd() {
        if (currentOperation != Operations.NONE) {
            equals()
        }
        userMemory.mPlus(displayNumber.toBigDecimal())
    }

    /**
     * Subtract the current display from the memory.
     */
    fun memorySubtract() {
        if (currentOperation != Operations.NONE) {
            equals()
        }
        userMemory.mSubtract(displayNumber.toBigDecimal())
    }

    /**
     * Shows the memory and clean.
     */
    fun memoryResultAndClean() {
        if (userMemory.isMemoryInUse) {
            applyResult(this.userMemory.mrc())
        }
    }

    /**
     * Performs the squareRoot operation.
     */
    fun squareRoot() {
        displayNumber.setValue(Math.sqrt(displayNumber.toBigDecimal().toDouble()).toString())
        applyResult(displayNumber.toBigDecimal())
    }

    /**
     * Performs the percent operation.
     */
    fun percent() {
        applyResult(currentTotal.multiply(displayNumber.toBigDecimal()).divide(BigDecimal("100")))
    }

    /**
     * Clean the display and reset the temp total.
     */
    fun ce() {
        currentTotal = BigDecimal(0)
        applyResult(currentTotal)
        currentOperation = Operations.NONE
        cleanDisplayOnNextInteraction = false
    }

    /**
     * Restores the state of the calculator.
     *
     * @param numberOnDisplay Number to show on display
     * @param currentCalcTotal Total of the calculation in progress
     * @param currentOperation Current selected operation
     * @param currentNumberInMemory Current Number in Memory
     * @param isMemoryInUse Whether Is Memory in Use or not
     * @param mustCleanDisplayOnNextInteraction Informs if the nex interaction must clean the Display
     * @param lastOperation Last Operation Executed
     * @param lastInputValue Input value used on the last operation.
     */
    fun restoreStatus(numberOnDisplay: String,
                      currentCalcTotal: String,
                      currentOperation: Operations,
                      currentNumberInMemory: String,
                      isMemoryInUse: Boolean,
                      mustCleanDisplayOnNextInteraction: Boolean,
                      lastOperation: Operations,
                      lastInputValue: String) {

        displayNumber.setValue(numberOnDisplay)
        this.currentTotal = try {
            BigDecimal(currentCalcTotal)
        } catch (e: Exception) {
            BigDecimal("0")
        }
        this.currentOperation = currentOperation
        this.userMemory.restoreMemoryStatus(currentNumberInMemory, isMemoryInUse)
        this.cleanDisplayOnNextInteraction = mustCleanDisplayOnNextInteraction
        this.lastOperation = lastOperation
        this.lastInput = try {
            BigDecimal(lastInputValue)
        } catch (e: Exception) {
            BigDecimal("0")
        }
    }

    /**
     * Gets the information if the memory is in use.
     */
    fun isMemoryInUse(): Boolean = userMemory.isMemoryInUse

    /**
     * Gets the current Memory Value.
     */
    fun getCurrentNumberInMemory(): BigDecimal = userMemory.currentMemoryValue()

    /**
     * Information if on the next interaction is necessary to clean the display
     */
    fun mustcleanDisplayOnNextInteraction(): Boolean = this.cleanDisplayOnNextInteraction

    /**
     * Gets the total of the current operation.
     */
    fun getCurrentTotal(): BigDecimal = currentTotal

    /**
     * Gets the last operation executed
     */
    fun getLastOperation(): Operations = lastOperation

    /**
     * Gets the last value used in the last operation.
     */
    fun getLastInputValue(): String = lastInput.toString()
}
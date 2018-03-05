package br.com.hitg.simplecalculator.calculator

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
    private var currentTotal: Double = 0.0

    /**
     * Current Operation.
     */
    private var currentOperation: Operations? = null

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
        displayNumber = "0"
        currentOperation = Operations.NONE
        cleanDisplayOnNextInteraction = false;
        currentTotal = 0.0
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
        displayNumber = currentTotal.toString()
        cleanDisplayOnNextInteraction = true
    }

    private fun updateTempMemory() {
        if (!cleanDisplayOnNextInteraction) {
            when (currentOperation) {
                Operations.ADDITION ->
                    currentTotal += displayNumber.toDouble()
                Operations.SUBTRACTION ->
                    currentTotal -= displayNumber.toDouble()
                Operations.DIVISION ->
                    currentTotal /= displayNumber.toDouble()
                Operations.MULTIPLICATION ->
                    currentTotal *= displayNumber.toDouble()
                Operations.NONE ->
                    currentTotal = displayNumber.toDouble()
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
        displayNumber = currentTotal.toString()
        currentTotal = 0.0
    }

    fun typeDot() {
        this.updateTempMemory()
        displayNumber = currentTotal.toString() + "."
    }

    fun isDotVisible(): Boolean = displayNumber.contains(".")

    fun memoryAdd() {
        this.userMemory.mPlus(displayNumber.toDouble())
    }

    fun memorySubtract() {
        this.userMemory.mSubtract(displayNumber.toDouble())
    }

    fun memoryResultAndClean() {
        displayNumber = this.userMemory.mrc().toString()
    }

    fun squareRoot() {
        displayNumber = Math.sqrt(displayNumber.toDouble()).toString()
    }

    fun percent() {
        displayNumber = (displayNumber.toDouble() * currentTotal / 100).toString()
    }

    fun ce() {
        initializeCalculator()
    }
}
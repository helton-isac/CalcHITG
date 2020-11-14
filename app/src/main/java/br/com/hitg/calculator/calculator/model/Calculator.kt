package br.com.hitg.calculator.calculator.model

import br.com.hitg.domain.mathEngine.MathEngine
import br.com.hitg.domain.mathEngine.SimpleMathEngine
import br.com.hitg.domain.usecases.AdditionUseCase
import br.com.hitg.domain.usecases.DivisionUseCase
import br.com.hitg.domain.usecases.MultiplicationUseCase
import br.com.hitg.domain.usecases.SubtractionUseCase
import com.hitg.data.local.model.CalculatorState
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.math.sqrt

/**
 * Created by Helton on 20/02/2018.
 *
 * Class responsible by managing the memory and calculations.
 */
class Calculator {

    // TODO: Use DI
    private val mathEngine: MathEngine = SimpleMathEngine()
    private val additionUseCase = AdditionUseCase(mathEngine)
    private val subtractionUseCase = SubtractionUseCase(mathEngine)
    private val multiplicationUseCase = MultiplicationUseCase(mathEngine)
    private val divisionUseCase = DivisionUseCase(mathEngine)

    var displayNumber: CalculatorDisplay = CalculatorDisplay(200)
    var userMemoryDisplayNumber: CalculatorDisplay = CalculatorDisplay(200)
    private var cleanDisplayOnNextInteraction: Boolean = false
    private var currentTotal: BigDecimal = BigDecimal("0")
    var currentOperation: Operations = Operations.NONE
        private set

    private val userMemory: CalculatorUserMemory = CalculatorUserMemory()
    private var lastOperation: Operations = Operations.NONE
    private var lastInput: BigDecimal = BigDecimal("0")

    init {
        ce()
        userMemory.mrc()
    }

    private fun applyResult(value: BigDecimal) {
        setFormattedValueOnDisplay(displayNumber, value)
        cleanDisplayOnNextInteraction = true
    }

    private fun setFormattedValueOnDisplay(display: CalculatorDisplay, value: BigDecimal) {
        val df = DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
        df.maximumFractionDigits = 340
        display.setValue(df.format(value))
    }

    fun typeNumber(number: Char) {
        if (displayNumber.appendNumber(number, cleanDisplayOnNextInteraction)) {
            cleanDisplayOnNextInteraction = false
        }
    }

    fun add() = performOperation(Operations.ADDITION)

    fun subtract() = performOperation(Operations.SUBTRACTION)

    fun divide() = performOperation(Operations.DIVISION)

    fun multiply() = performOperation(Operations.MULTIPLICATION)

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
            Operations.ADDITION -> additionUseCase.execute(value1, value2)
            Operations.SUBTRACTION -> subtractionUseCase.execute(value1, value2)
            Operations.DIVISION -> divisionUseCase.execute(value1, value2)
            Operations.MULTIPLICATION -> multiplicationUseCase.execute(value1, value2)
            Operations.NONE -> value2
        }
    }

    fun removeLast() {
        if (!displayNumber.removeLast()) {
            currentOperation = Operations.NONE
            lastOperation = Operations.NONE
        }
        cleanDisplayOnNextInteraction = false
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
        if (currentOperation != Operations.NONE) {
            equals()
        }
        userMemory.mPlus(displayNumber.toBigDecimal())
        setFormattedValueOnDisplay(userMemoryDisplayNumber, userMemory.currentMemoryValue())
    }

    fun memorySubtract() {
        if (currentOperation != Operations.NONE) {
            equals()
        }
        userMemory.mSubtract(displayNumber.toBigDecimal())
        setFormattedValueOnDisplay(userMemoryDisplayNumber, userMemory.currentMemoryValue())
    }

    fun memoryResultAndClean() {
        if (userMemory.isMemoryInUse) {
            applyResult(this.userMemory.mrc())
        }
        setFormattedValueOnDisplay(userMemoryDisplayNumber, userMemory.currentMemoryValue())
    }

    fun squareRoot() = applyResult(BigDecimal(sqrt(displayNumber.toBigDecimal().toDouble()).toString()))

    fun percent() = applyResult(currentTotal.multiply(displayNumber.toBigDecimal()).divide(BigDecimal("100")))

    fun invertSignal() = applyResult(displayNumber.toBigDecimal().multiply(BigDecimal("-1")))

    fun ce() {
        currentTotal = BigDecimal(0)
        applyResult(currentTotal)
        currentOperation = Operations.NONE
        cleanDisplayOnNextInteraction = false
    }

    fun applyStatus(calculatorState: CalculatorState) {
        displayNumber.setValue(calculatorState.displayValue)
        this.currentTotal = try {
            BigDecimal(calculatorState.calcTotal)
        } catch (e: Exception) {
            BigDecimal("0")
        }
        this.currentOperation = Operations.valueOf(calculatorState.currentOperation)
        this.userMemory.restoreMemoryStatus(
                calculatorState.numberInMemory,
                calculatorState.isMemoryInUse)
        setFormattedValueOnDisplay(userMemoryDisplayNumber, this.userMemory.currentMemoryValue())
        this.cleanDisplayOnNextInteraction = calculatorState.mustCleanDisplayOnNextInteraction
        this.lastOperation = Operations.valueOf(calculatorState.lastOperation)
        this.lastInput = try {
            BigDecimal(calculatorState.lastInputValue)
        } catch (e: Exception) {
            BigDecimal("0")
        }
    }

    fun isMemoryInUse(): Boolean = userMemory.isMemoryInUse

    fun getCurrentNumberInMemory(): BigDecimal = userMemory.currentMemoryValue()

    fun mustCleanDisplayOnNextInteraction(): Boolean = this.cleanDisplayOnNextInteraction

    fun getCurrentTotal(): BigDecimal = currentTotal

    fun getLastOperation(): Operations = lastOperation

    fun getLastInputValue(): String = lastInput.toString()
}
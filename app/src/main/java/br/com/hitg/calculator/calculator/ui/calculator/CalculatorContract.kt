package br.com.hitg.calculator.calculator.ui.calculator

import br.com.hitg.calculator.calculator.model.Operations
import br.com.hitg.calculator.calculator.ui.BasePresenter
import br.com.hitg.calculator.calculator.ui.BaseView

/**
 * Created by Helton on 20/02/2018.
 *
 * Contract to be used between Presenter and View.
 */
interface CalculatorContract {

    enum class CalculatorNumbers {

        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        ZERO,
    }

    interface View : BaseView<Presenter> {
        fun updateDisplay(value: String)
        fun updateOperation(currentOperation: Operations)
        fun updateMemoryDisplay(isMemoryInUse: Boolean, value: String)
    }

    interface Presenter : BasePresenter {
        fun typeNumber(key: CalculatorNumbers)
        fun add()
        fun minus()
        fun divide()
        fun multiply()
        fun typeDot()
        fun typeEquals()
        fun memoryAdd()
        fun memorySubtract()
        fun memoryResultAndClean()
        fun squareRoot()
        fun percent()
        fun removeLast()
        fun invertSignal()
        fun ce()
        fun restoreCalculatorState(numberOnDisplay: String,
                                   currentCalcTotal: String,
                                   currentOperation: Operations,
                                   currentNumberInMemory: String,
                                   isMemoryInUse: Boolean,
                                   mustCleanDisplayOnNextInteraction: Boolean,
                                   lastOperation: Operations,
                                   lastInputValue: String)
        val currentDisplayValue: String
        val currentCalcTotal: String
        val currentOperation: Operations
        val currentNumberInMemory: String
        val isMemoryInUse: Boolean
        val mustCleanDisplayOnNextInteraction: Boolean
        val lastOperation: Operations
        val lastInputValue: String
    }

}
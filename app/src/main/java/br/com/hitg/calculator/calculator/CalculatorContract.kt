package br.com.hitg.calculator.calculator

import br.com.hitg.calculator.BasePresenter
import br.com.hitg.calculator.BaseView

/**
 * Created by Helton on 20/02/2018.
 *
 * Contract to be used between Presenter and View.
 */
interface CalculatorContract {

    /**
     * Available Numbers in the Calculator
     */
    enum class CalculatorNumbers {

        /**
         * Number 1
         */
        ONE,
        /**
         * Number 2
         */
        TWO,
        /**
         * Number 3
         */
        THREE,
        /**
         * Number 4
         */
        FOUR,
        /**
         * Number 5
         */
        FIVE,
        /**
         * Number 6
         */
        SIX,
        /**
         * Number 7
         */
        SEVEN,
        /**
         * Number 8
         */
        EIGHT,
        /**
         * Number 9
         */
        NINE,
        /**
         * Number 0
         */
        ZERO,
    }

    /**
     * View interface.
     *
     * It must contains all methods that should be implemented by the View.
     */
    interface View : BaseView<Presenter> {
        /**
         * Update the display of the Calculator.
         *
         * @param value Value to show.
         */
        fun updateDisplay(value: String)

        /**
         * Show the current Operation.
         *
         * @currentOperation Current Operation.
         */
        fun updateOperation(currentOperation: Operations)

        /**
         * Update the display of the User Memory Calculator.
         *
         * @param isMemoryInUse Whether must show memory or not
         * @param value Value to show.
         */
        fun updateMemoryDisplay(isMemoryInUse: Boolean, value: String)

        /**
         * Closes the application.
         */
        fun finish()
    }

    /**
     * Presenter interface.
     *
     * It must contains all methods that should be implemented by the Presenter.
     */
    interface Presenter : BasePresenter {

        /**
         * Concatenate the typed number with the number in display.
         *
         * @param key Number typed.
         */
        fun typeNumber(key: CalculatorNumbers)

        /**
         * Resume the current calculation and start a addition operation.
         */
        fun add()

        /**
         * Resume the current calculation and start a subtraction operation.
         */
        fun minus()

        /**
         * Resume the current calculation and start a division operation.
         */
        fun divide()

        /**
         * Resume the current calculation and start a multiplication operation.
         */
        fun multiply()

        /**
         * Called when the dot is typed.
         */
        fun typeDot()

        /**
         * Called when the equals sign is typed.
         */
        fun typeEquals()

        /**
         * Add the current display number in the memory
         */
        fun memoryAdd()

        /**
         * Subtract the current display number from the memory
         */
        fun memorySubtract()

        /**
         * Display the result and clean the memory
         */
        fun memoryResultAndClean()

        /**
         * Calculate the square root of the number displayed
         * and update the display with the result.
         */
        fun squareRoot()

        /**
         * Multiply the number in display for the last number in the calculator memory,
         * divide it by 100 and shows the new number in the display.
         */
        fun percent()

        /**
         * Remove the last Typed Character or operation
         */
        fun removeLast()

        /**
         * Invert Signal.
         */
        fun invertSignal()

        /**
         * Clean the temp memory and the display
         */
        fun ce()

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
        fun restoreCalculatorState(numberOnDisplay: String,
                                   currentCalcTotal: String,
                                   currentOperation: Operations,
                                   currentNumberInMemory: String,
                                   isMemoryInUse: Boolean,
                                   mustCleanDisplayOnNextInteraction: Boolean,
                                   lastOperation: Operations,
                                   lastInputValue: String)

        /**
         * Current Value on Display.
         */
        val currentDisplayValue: String

        /**
         * Current internal calculator value.
         */
        val currentCalcTotal: String

        /**
         * Operation in execution.
         */
        val currentOperation: Operations

        /**
         * Current number stored in memory.
         */
        val currentNumberInMemory: String

        /**
         * Is using memory? In case of the current memory is zero.
         */
        val isMemoryInUse: Boolean

        /**
         * Must clean the Display in the next interaction?
         */
        val mustcleanDisplayOnNextInteraction: Boolean

        /**
         * Last executed operation.
         */
        val lastOperation: Operations

        /**
         * Value used in the last operation.
         */
        val lastInputValue: String
    }

}
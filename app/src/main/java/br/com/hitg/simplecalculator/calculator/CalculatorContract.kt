package br.com.hitg.simplecalculator.calculator

import br.com.hitg.simplecalculator.BasePresenter
import br.com.hitg.simplecalculator.BaseView

/**
 * Created by Helton on 20/02/2018.
 *
 * Contract to be used between Presenter and View.
 */
interface CalculatorContract {

    /**
     * Available Numbers in the Simple Calculator
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
         */
        fun updateDisplay(value: String)

        fun updateOperation(operation: Operations)

        fun finish()

        fun showHideMemory(memoryInUse: Boolean)
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
        fun backspace()

        /**
         * Quit from the calculator.
         */
        fun exit()

        /**
         * Clean the temp memory and the display
         */
        fun ce()
    }

}
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
     * Available Keys in the Simple Calculator
     */
    enum class CalculatorNumbers {

        /**
         * Key 1
         */
        ONE,
        /**
         * Key 2
         */
        TWO,
        /**
         * Key 3
         */
        THREE,
        /**
         * Key 4
         */
        FOUR,
        /**
         * Key 5
         */
        FIVE,
        /**
         * Key 6
         */
        SIX,
        /**
         * Key 7
         */
        SEVEN,
        /**
         * Key 8
         */
        EIGHT,
        /**
         * Key 9
         */
        NINE,
        /**
         * Key 0
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
         */
        fun squareRoot()

        /**
         * Multiply the number in display for the last number in the calculator memory,
         * divide it by 100 and finalize the calculation.
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
        fun resetCalculator()
    }

}
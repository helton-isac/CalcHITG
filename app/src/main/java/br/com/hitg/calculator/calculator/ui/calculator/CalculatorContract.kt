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

    interface View : BaseView<Presenter> {
        fun updateDisplay(value: String)
        fun updateOperation(currentOperation: Operations)
        fun updateMemoryDisplay(isMemoryInUse: Boolean, value: String)
    }

    interface Presenter : BasePresenter {

        fun buttonOneClicked()
        fun buttonTwoClicked()
        fun buttonThreeClicked()
        fun buttonFourClicked()
        fun buttonFiveClicked()
        fun buttonSixClicked()
        fun buttonSevenClicked()
        fun buttonEightClicked()
        fun buttonNineClicked()
        fun buttonZeroClicked()
        fun buttonAddClicked()
        fun buttonMinusClicked()
        fun buttonDivideClicked()
        fun buttonMultiplyClicked()
        fun buttonDotClicked()
        fun buttonEqualsClicked()
        fun buttonMPlusClicked()
        fun buttonMMinusClicked()
        fun buttonMrcClicked()
        fun buttonSquareRootClicked()
        fun buttonPercentClicked()
        fun buttonCeClicked()
        fun buttonInvertSignalClicked()

        fun removeLast()
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
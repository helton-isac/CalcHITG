package br.com.hitg.calculator.calculator.ui.calculator

import br.com.hitg.calculator.calculator.model.Calculator
import br.com.hitg.calculator.calculator.model.Operations

class CalculatorPresenter(private val calculatorView: CalculatorContract.View) :
        CalculatorContract.Presenter {

    private val calculator: Calculator

    init {
        calculatorView.presenter = this
        calculator = Calculator()
    }

    override fun start() {
        updateView()
    }

    private fun executeOperationAndUpdateDisplay(operationMethod: () -> Unit) {
        operationMethod()
        updateView()
    }

    private fun updateView() {
        calculatorView.updateDisplay(calculator.displayNumber.toString())
        calculatorView.updateOperation(calculator.currentOperation)
        calculatorView.updateMemoryDisplay(calculator.isMemoryInUse(),
                calculator.userMemoryDisplayNumber.toString())
    }

    override fun buttonOneClicked() {
        calculator.typeNumber('1')
        updateView()
    }

    override fun buttonTwoClicked() {
        calculator.typeNumber('2')
        updateView()
    }

    override fun buttonThreeClicked() {
        calculator.typeNumber('3')
        updateView()
    }

    override fun buttonFourClicked() {
        calculator.typeNumber('4')
        updateView()
    }

    override fun buttonFiveClicked() {
        calculator.typeNumber('5')
        updateView()
    }

    override fun buttonSixClicked() {
        calculator.typeNumber('6')
        updateView()
    }

    override fun buttonSevenClicked() {
        calculator.typeNumber('7')
        updateView()
    }

    override fun buttonEightClicked() {
        calculator.typeNumber('8')
        updateView()
    }

    override fun buttonNineClicked() {
        calculator.typeNumber('9')
        updateView()
    }

    override fun buttonZeroClicked() {
        calculator.typeNumber('0')
        updateView()
    }

    override fun buttonAddClicked() {
        executeOperationAndUpdateDisplay { calculator.add() }
    }

    override fun buttonMinusClicked() {
        executeOperationAndUpdateDisplay { calculator.subtract() }
    }

    override fun buttonDivideClicked() {
        executeOperationAndUpdateDisplay { calculator.divide() }
    }

    override fun buttonMultiplyClicked() {
        executeOperationAndUpdateDisplay { calculator.multiply() }
    }

    override fun buttonDotClicked() {
        executeOperationAndUpdateDisplay { calculator.typeDot() }
    }

    override fun buttonEqualsClicked() {
        executeOperationAndUpdateDisplay { calculator.equals() }
    }

    override fun buttonMPlusClicked() {
        executeOperationAndUpdateDisplay { calculator.memoryAdd() }
    }

    override fun buttonMMinusClicked() {
        executeOperationAndUpdateDisplay { calculator.memorySubtract() }
    }

    override fun buttonMrcClicked() {
        executeOperationAndUpdateDisplay { calculator.memoryResultAndClean() }
    }

    override fun buttonSquareRootClicked() {
        executeOperationAndUpdateDisplay { calculator.squareRoot() }
    }

    override fun buttonPercentClicked() {
        executeOperationAndUpdateDisplay { calculator.percent() }
    }

    override fun removeLast() {
        executeOperationAndUpdateDisplay { calculator.removeLast() }
    }

    override fun buttonCeClicked() {
        executeOperationAndUpdateDisplay { calculator.ce() }
    }

    override fun buttonInvertSignalClicked() {
        executeOperationAndUpdateDisplay { calculator.invertSignal() }
    }


    override fun restoreCalculatorState(numberOnDisplay: String,
                                        currentCalcTotal: String,
                                        currentOperation: Operations,
                                        currentNumberInMemory: String,
                                        isMemoryInUse: Boolean,
                                        mustCleanDisplayOnNextInteraction: Boolean,
                                        lastOperation: Operations,
                                        lastInputValue: String) {
        calculator.restoreStatus(numberOnDisplay,
                currentCalcTotal,
                currentOperation,
                currentNumberInMemory,
                isMemoryInUse,
                mustCleanDisplayOnNextInteraction,
                lastOperation,
                lastInputValue)
        updateView()

    }

    override val currentOperation: Operations
        get() = calculator.currentOperation
    override val currentCalcTotal: String
        get() = calculator.getCurrentTotal().toString()
    override val currentDisplayValue: String
        get() = calculator.displayNumber.toString()
    override val currentNumberInMemory: String
        get() = calculator.getCurrentNumberInMemory().toString()
    override val isMemoryInUse: Boolean
        get() = calculator.isMemoryInUse()
    override val mustCleanDisplayOnNextInteraction: Boolean
        get() = calculator.mustCleanDisplayOnNextInteraction()
    override val lastOperation: Operations
        get() = calculator.getLastOperation()
    override val lastInputValue: String
        get() = calculator.getLastInputValue()
}

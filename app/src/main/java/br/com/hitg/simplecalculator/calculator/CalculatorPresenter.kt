package br.com.hitg.simplecalculator.calculator

import br.com.hitg.simplecalculator.calculator.CalculatorContract.CalculatorNumbers


class CalculatorPresenter(val calculatorView: CalculatorContract.View) :
        CalculatorContract.Presenter {

    private val calculator: Calculator

    init {
        calculatorView.presenter = this
        calculator = Calculator(8)
        updateView()
    }

    override fun start() {

    }

    private fun executeOperationAndUpdateDisplay(operationMethod: () -> Unit) {
        operationMethod()
        updateView()
    }

    private fun updateView() {
        calculatorView.updateDisplay(calculator.displayNumber)
        calculatorView.updateOperation(calculator.currentOperation)
        calculatorView.showHideMemory(calculator.isMemoryInUse())
    }

    override fun add() {
        executeOperationAndUpdateDisplay { calculator.add() }
    }

    override fun minus() {
        executeOperationAndUpdateDisplay { calculator.subtract() }
    }

    override fun divide() {
        executeOperationAndUpdateDisplay { calculator.divide() }
    }

    override fun multiply() {
        executeOperationAndUpdateDisplay { calculator.multiply() }
    }

    override fun typeDot() {
        executeOperationAndUpdateDisplay { calculator.typeDot() }
    }

    override fun typeEquals() {
        executeOperationAndUpdateDisplay { calculator.equals() }
    }

    override fun memoryAdd() {
        executeOperationAndUpdateDisplay { calculator.memoryAdd() }
    }

    override fun memorySubtract() {
        executeOperationAndUpdateDisplay { calculator.memorySubtract() }
    }

    override fun memoryResultAndClean() {
        executeOperationAndUpdateDisplay { calculator.memoryResultAndClean() }
    }

    override fun squareRoot() {
        executeOperationAndUpdateDisplay { calculator.squareRoot() }
    }

    override fun percent() {
        executeOperationAndUpdateDisplay { calculator.percent() }
    }

    override fun backspace() {
        executeOperationAndUpdateDisplay { calculator.backspace() }
    }

    override fun ce() {
        executeOperationAndUpdateDisplay { calculator.ce() }
    }

    override fun exit() {
        calculatorView.finish()
    }


    override fun typeNumber(key: CalculatorNumbers) {
        when (key) {
            CalculatorNumbers.ZERO -> calculator.typeNumber(0)
            CalculatorNumbers.ONE -> calculator.typeNumber(1)
            CalculatorNumbers.TWO -> calculator.typeNumber(2)
            CalculatorNumbers.THREE -> calculator.typeNumber(3)
            CalculatorNumbers.FOUR -> calculator.typeNumber(4)
            CalculatorNumbers.FIVE -> calculator.typeNumber(5)
            CalculatorNumbers.SIX -> calculator.typeNumber(6)
            CalculatorNumbers.SEVEN -> calculator.typeNumber(7)
            CalculatorNumbers.EIGHT -> calculator.typeNumber(8)
            CalculatorNumbers.NINE -> calculator.typeNumber(9)
        }
        updateView()
    }

    var calcMemory: Int = 0

    var displayNumber: String = "0"

    var hasDecimals: Boolean = false

    var cleanDisplay: Boolean = false


}
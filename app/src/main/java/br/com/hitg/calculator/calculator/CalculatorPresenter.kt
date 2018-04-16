package br.com.hitg.calculator.calculator

import br.com.hitg.calculator.calculator.CalculatorContract.CalculatorNumbers


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
        calculatorView.showMemoryIndicator(calculator.isMemoryInUse())
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

    override fun removeLast() {
        executeOperationAndUpdateDisplay { calculator.removeLast() }
    }

    override fun ce() {
        executeOperationAndUpdateDisplay { calculator.ce() }
    }

    override fun exit() {
        calculatorView.finish()
    }


    override fun typeNumber(key: CalculatorNumbers) {
        when (key) {
            CalculatorNumbers.ZERO  -> calculator.typeNumber('0')
            CalculatorNumbers.ONE   -> calculator.typeNumber('1')
            CalculatorNumbers.TWO   -> calculator.typeNumber('2')
            CalculatorNumbers.THREE -> calculator.typeNumber('3')
            CalculatorNumbers.FOUR  -> calculator.typeNumber('4')
            CalculatorNumbers.FIVE  -> calculator.typeNumber('5')
            CalculatorNumbers.SIX   -> calculator.typeNumber('6')
            CalculatorNumbers.SEVEN -> calculator.typeNumber('7')
            CalculatorNumbers.EIGHT -> calculator.typeNumber('8')
            CalculatorNumbers.NINE  -> calculator.typeNumber('9')
        }
        updateView()
    }

    override fun restoreCalculatorState(displayValue: String,
                                        currentCalcTotal: String,
                                        currentOperation: Operations,
                                        currentNumberInMemory: String,
                                        isMemoryInUse: Boolean,
                                        mustcleanDisplayOnNextInteraction: Boolean,
                                        lastOperation: Operations,
                                        lastInputValue: String) {
        calculator.restoreStatus(displayValue,
                currentCalcTotal,
                currentOperation,
                currentNumberInMemory,
                isMemoryInUse,
                mustcleanDisplayOnNextInteraction,
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
    override val mustcleanDisplayOnNextInteraction: Boolean
        get() = calculator.mustcleanDisplayOnNextInteraction()
    override val lastOperation: Operations
        get() = calculator.getLastOperation()
    override val lastInputValue: String
        get() = calculator.getLastInputValue()
}

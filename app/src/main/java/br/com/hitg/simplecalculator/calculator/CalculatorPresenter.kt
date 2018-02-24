package br.com.hitg.simplecalculator.calculator

import br.com.hitg.simplecalculator.calculator.CalculatorContract.CalculatorNumbers


class CalculatorPresenter(val calculatorView: CalculatorContract.View) : CalculatorContract.Presenter {

    override fun add() {
        calculator.add()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun minus() {
        calculator.subtract()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun divide() {
        calculator.divide()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun multiply() {
        calculator.multiply()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun typeDot() {
        calculator.dot()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun typeEquals() {
        calculator.equals()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun memoryAdd() {
        calculator.memoryAdd()
    }

    override fun memorySubtract() {
        calculator.memorySubtract()
    }

    override fun memoryResultAndClean() {
        calculator.memoryResultAndClean()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun squareRoot() {
        calculator.squareRoot()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun percent() {
        calculator.percent()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun backspace() {
        calculator.backspace()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    override fun exit() {
        calculatorView.finish()
    }

    override fun resetCalculator() {
        calculator.ce()
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    private val calculator: Calculator

    init {
        calculatorView.presenter = this
        calculator = Calculator(8)
    }

    override fun start() {

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
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    var calcMemory: Int = 0

    var displayNumber: String = "0"

    var hasDecimals: Boolean = false

    var cleanDisplay: Boolean = false


}
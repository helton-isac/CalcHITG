package br.com.hitg.simplecalculator.calculator


class CalculatorPresenter(val calculatorView:CalculatorContract.View) : CalculatorContract.Presenter {
    override var currentOperation: Operations? = null

    private val calculator: Calculator

    init {
        calculatorView.presenter = this
        calculator = Calculator()
    }

    override fun start() {

    }

    var memory: Double = 0.0

    override fun typeKey(key: CalculatorKeys) {
        when (key){
            CalculatorKeys.ZERO -> calculator.typeNumber(0)
            CalculatorKeys.ONE-> calculator.typeNumber(1)
            CalculatorKeys.TWO-> calculator.typeNumber(2)
            CalculatorKeys.THREE-> calculator.typeNumber(3)
            CalculatorKeys.FOUR-> calculator.typeNumber(4)
            CalculatorKeys.FIVE-> calculator.typeNumber(5)
            CalculatorKeys.SIX-> calculator.typeNumber(6)
            CalculatorKeys.SEVEN-> calculator.typeNumber(7)
            CalculatorKeys.EIGHT-> calculator.typeNumber(8)
            CalculatorKeys.NINE-> calculator.typeNumber(9)
            CalculatorKeys.DOT-> calculator.typeNumber(0)
            CalculatorKeys.MULTIPLICATION-> calculator.typeNumber(0)
            CalculatorKeys.DIVISION-> calculator.typeNumber(0)
            CalculatorKeys.MINUS-> calculator.typeNumber(0)
            CalculatorKeys.PLUS-> calculator.typeNumber(0)
            CalculatorKeys.EQUALS-> calculator.typeNumber(0)
            CalculatorKeys.BACKSPACE-> calculator.typeNumber(0)
            CalculatorKeys.CE-> calculator.typeNumber(0)
            CalculatorKeys.MMINUS-> calculator.typeNumber(0)
            CalculatorKeys.MPLUS-> calculator.typeNumber(0)
            CalculatorKeys.MRC-> calculator.typeNumber(0)
            CalculatorKeys.OFF-> calculator.typeNumber(0)
            CalculatorKeys.PERCENT-> calculator.typeNumber(0)
            CalculatorKeys.SQUARE_ROOT-> calculator.typeNumber(0)
        }
        calculatorView.updateDisplay(calculator.displayNumber)
    }

    var calcMemory: Int = 0

    var displayNumber: String = "0"

    var hasDecimals: Boolean = false

    var cleanDisplay: Boolean = false




}
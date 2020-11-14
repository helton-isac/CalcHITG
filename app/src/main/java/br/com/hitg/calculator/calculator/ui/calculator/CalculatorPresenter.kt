package br.com.hitg.calculator.calculator.ui.calculator

import android.app.Activity
import android.content.Context
import br.com.hitg.calculator.calculator.model.Calculator
import com.hitg.data.local.database.CalculatorStateSharedPrefImpl
import com.hitg.data.local.datasource.CalculatorStateDataSource
import com.hitg.data.local.datasource.CalculatorStateDataSourceImpl
import com.hitg.data.local.model.CalculatorState

class CalculatorPresenter(
        private val calculatorView: CalculatorContract.View,
        context: Activity
) :
        CalculatorContract.Presenter {

    private val calculator: Calculator
    private val calculatorStateDataSource: CalculatorStateDataSource

    init {
        calculatorView.presenter = this
        calculator = Calculator()
        calculatorStateDataSource =
                CalculatorStateDataSourceImpl(
                        CalculatorStateSharedPrefImpl(
                                context.getPreferences(Context.MODE_PRIVATE)))
    }

    override fun start() {
        calculator.applyState(calculatorStateDataSource.getCalculatorState())
        updateView()
    }

    private fun executeOperationAndUpdateDisplay(operationMethod: () -> Unit) {
        operationMethod()
        updateView()
        persistCalculatorState()
    }

    private fun updateView() {
        calculatorView.updateDisplay(calculator.displayNumber.toString())
        calculatorView.updateOperation(calculator.currentOperation)
        calculatorView.updateMemoryDisplay(calculator.isMemoryInUse(),
                calculator.userMemoryDisplayNumber.toString())
    }

    override fun buttonOneClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('1') }

    override fun buttonTwoClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('2') }

    override fun buttonThreeClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('3') }

    override fun buttonFourClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('4') }

    override fun buttonFiveClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('5') }

    override fun buttonSixClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('6') }

    override fun buttonSevenClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('7') }

    override fun buttonEightClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('8') }

    override fun buttonNineClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('9') }

    override fun buttonZeroClicked() = executeOperationAndUpdateDisplay { calculator.typeNumber('0') }

    override fun buttonAddClicked() = executeOperationAndUpdateDisplay { calculator.add() }

    override fun buttonMinusClicked() = executeOperationAndUpdateDisplay { calculator.subtract() }

    override fun buttonDivideClicked() = executeOperationAndUpdateDisplay { calculator.divide() }

    override fun buttonMultiplyClicked() = executeOperationAndUpdateDisplay { calculator.multiply() }

    override fun buttonDotClicked() = executeOperationAndUpdateDisplay { calculator.typeDot() }

    override fun buttonEqualsClicked() = executeOperationAndUpdateDisplay { calculator.equals() }

    override fun buttonMPlusClicked() = executeOperationAndUpdateDisplay { calculator.memoryAdd() }

    override fun buttonMMinusClicked() = executeOperationAndUpdateDisplay { calculator.memorySubtract() }

    override fun buttonMrcClicked() = executeOperationAndUpdateDisplay { calculator.memoryResultAndClean() }

    override fun buttonSquareRootClicked() = executeOperationAndUpdateDisplay { calculator.squareRoot() }

    override fun buttonPercentClicked() = executeOperationAndUpdateDisplay { calculator.percent() }

    override fun buttonDelClicked() = executeOperationAndUpdateDisplay { calculator.removeLast() }

    override fun buttonCeClicked() = executeOperationAndUpdateDisplay { calculator.ce() }

    override fun buttonInvertSignalClicked() = executeOperationAndUpdateDisplay { calculator.invertSignal() }

    private fun persistCalculatorState() {
        val calculatorState = CalculatorState(
                calculator.displayNumber.toString(),
                calculator.getCurrentTotal().toString(),
                calculator.currentOperation.name,
                calculator.getCurrentNumberInMemory().toString(),
                calculator.isMemoryInUse(),
                calculator.mustCleanDisplayOnNextInteraction(),
                calculator.getLastOperation().name,
                calculator.getLastInputValue(),
        )
        calculatorStateDataSource.persistCalculatorState(calculatorState)
    }
}

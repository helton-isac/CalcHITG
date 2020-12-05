package br.com.hitg.calculator.calculator.ui.calculator

import android.app.Activity
import android.content.Context
import br.com.hitg.domain.model.Calculator
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

    private fun processInteractionAndUpdateDisplay(interaction: () -> Unit) {
        interaction()
        updateView()
        persistCalculatorState()
    }

    private fun updateView() {
        calculatorView.updateDisplay(calculator.displayNumber.toString())
        calculatorView.showOperation(calculator.currentOperation)
        calculatorView.updateMemoryDisplay(calculator.isMemoryInUse(),
                calculator.userMemoryDisplayNumber.toString())
    }

    override fun buttonOneClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('1') }

    override fun buttonTwoClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('2') }

    override fun buttonThreeClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('3') }

    override fun buttonFourClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('4') }

    override fun buttonFiveClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('5') }

    override fun buttonSixClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('6') }

    override fun buttonSevenClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('7') }

    override fun buttonEightClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('8') }

    override fun buttonNineClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('9') }

    override fun buttonZeroClicked() = processInteractionAndUpdateDisplay { calculator.typeNumber('0') }

    override fun buttonAddClicked() = processInteractionAndUpdateDisplay { calculator.add() }

    override fun buttonMinusClicked() = processInteractionAndUpdateDisplay { calculator.subtract() }

    override fun buttonDivideClicked() = processInteractionAndUpdateDisplay { calculator.divide() }

    override fun buttonMultiplyClicked() = processInteractionAndUpdateDisplay { calculator.multiply() }

    override fun buttonDotClicked() = processInteractionAndUpdateDisplay { calculator.typeDot() }

    override fun buttonEqualsClicked() = processInteractionAndUpdateDisplay { calculator.equals() }

    override fun buttonMPlusClicked() = processInteractionAndUpdateDisplay { calculator.memoryAdd() }

    override fun buttonMMinusClicked() = processInteractionAndUpdateDisplay { calculator.memorySubtract() }

    override fun buttonMrcClicked() = processInteractionAndUpdateDisplay { calculator.memoryResultAndClean() }

    override fun buttonSquareRootClicked() = processInteractionAndUpdateDisplay { calculator.squareRoot() }

    override fun buttonPercentClicked() = processInteractionAndUpdateDisplay { calculator.percent() }

    override fun buttonDelClicked() = processInteractionAndUpdateDisplay { calculator.removeLast() }

    override fun buttonCeClicked() = processInteractionAndUpdateDisplay { calculator.ce() }

    override fun buttonInvertSignalClicked() = processInteractionAndUpdateDisplay { calculator.invertSignal() }

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

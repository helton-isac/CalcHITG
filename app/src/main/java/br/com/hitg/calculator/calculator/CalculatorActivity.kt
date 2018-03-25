package br.com.hitg.calculator.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.hitg.calculator.R
import br.com.hitg.calculator.calculator.CalculatorContract.CalculatorNumbers
import com.crashlytics.android.Crashlytics
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {

    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // TODO: Iniciar o Model/Presenter com o basico para o estado da calculadora:
        // Display Number - User Memoria - Última Operação

        // Create the presenter
        presenter = CalculatorPresenter(this)
    }

    open
    fun onClickButton(view: View?) {
        try {

            when (view?.id) {
                R.id.btnZero -> presenter.typeNumber(CalculatorNumbers.ZERO)
                R.id.btnOne -> presenter.typeNumber(CalculatorNumbers.ONE)
                R.id.btnTwo -> presenter.typeNumber(CalculatorNumbers.TWO)
                R.id.btnThree -> presenter.typeNumber(CalculatorNumbers.THREE)
                R.id.btnFour -> presenter.typeNumber(CalculatorNumbers.FOUR)
                R.id.btnFive -> presenter.typeNumber(CalculatorNumbers.FIVE)
                R.id.btnSix -> presenter.typeNumber(CalculatorNumbers.SIX)
                R.id.btnSeven -> presenter.typeNumber(CalculatorNumbers.SEVEN)
                R.id.btnEight -> presenter.typeNumber(CalculatorNumbers.EIGHT)
                R.id.btnNine -> presenter.typeNumber(CalculatorNumbers.NINE)
                R.id.btnDot -> presenter.typeDot()
                R.id.btnCE -> presenter.ce()
                R.id.btnOff -> presenter.exit()
                R.id.btnBack -> presenter.backspace()
                R.id.btnEquals -> presenter.typeEquals()
                R.id.btnPlus -> presenter.add()
                R.id.btnMinus -> presenter.minus()
                R.id.btnDivide -> presenter.divide()
                R.id.btnMultiply -> presenter.multiply()
                R.id.btnPercent -> presenter.percent()
                R.id.btnSquareRoot -> presenter.squareRoot()
                R.id.btnMRC -> presenter.memoryResultAndClean()
                R.id.btnMMinus -> presenter.memorySubtract()
                R.id.btnMPlus -> presenter.memoryAdd()
                else -> return
            }
        } catch (ex: Exception) {
            presenter = CalculatorPresenter(this)
            txtDisplay.setText(R.string.error)
            Crashlytics.logException(ex);
        }
    }

    override fun updateDisplay(value: String) {
        txtDisplay.text = value
    }

    override fun updateOperation(operation: Operations) {
        when (operation) {
            Operations.NONE -> txtSignals.text = ""
            Operations.ADDITION -> txtSignals.setText(R.string.plus_sign)
            Operations.SUBTRACTION -> txtSignals.setText(R.string.minus_sign)
            Operations.MULTIPLICATION -> txtSignals.setText(R.string.multiplication_sign)
            Operations.DIVISION -> txtSignals.setText(R.string.division_sign)
        }

    }

    override fun showHideMemory(memoryInUse: Boolean) {
        txtMemory.visibility = if (memoryInUse) View.VISIBLE else View.GONE
    }
}

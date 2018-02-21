package br.com.hitg.simplecalculator.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.hitg.simplecalculator.R
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {
    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // Create the presenter
        presenter = CalculatorPresenter(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //TODO: IMPLEMENT IT
        super.onSaveInstanceState(outState)
    }

    fun onClickButton(v: View?) {
        when (v?.id) {
            R.id.btnZero -> presenter.typeKey(CalculatorKeys.ZERO)
            R.id.btnOne -> presenter.typeKey(CalculatorKeys.ONE)
            R.id.btnTwo -> presenter.typeKey(CalculatorKeys.TWO)
            R.id.btnThree -> presenter.typeKey(CalculatorKeys.THREE)
            R.id.btnFour -> presenter.typeKey(CalculatorKeys.FOUR)
            R.id.btnFive -> presenter.typeKey(CalculatorKeys.FIVE)
            R.id.btnSix -> presenter.typeKey(CalculatorKeys.SIX)
            R.id.btnSeven -> presenter.typeKey(CalculatorKeys.SEVEN)
            R.id.btnEight -> presenter.typeKey(CalculatorKeys.EIGHT)
            R.id.btnNine -> presenter.typeKey(CalculatorKeys.NINE)
            R.id.btnDot -> presenter.typeKey(CalculatorKeys.DOT)
            R.id.btnCE -> presenter.typeKey(CalculatorKeys.CE)
            R.id.btnOff -> presenter.typeKey(CalculatorKeys.OFF)
            R.id.btnBack -> presenter.typeKey(CalculatorKeys.BACKSPACE)
            R.id.btnEquals -> presenter.typeKey(CalculatorKeys.EQUALS)
            R.id.btnPlus -> presenter.typeKey(CalculatorKeys.PLUS)
            R.id.btnMinus -> presenter.typeKey(CalculatorKeys.MINUS)
            R.id.btnDivide -> presenter.typeKey(CalculatorKeys.DIVISION)
            R.id.btnMultiply -> presenter.typeKey(CalculatorKeys.MULTIPLICATION)
            R.id.btnPercent -> presenter.typeKey(CalculatorKeys.PERCENT)
            R.id.btnSquareRoot -> presenter.typeKey(CalculatorKeys.SQUARE_ROOT)
            R.id.btnMRC -> presenter.typeKey(CalculatorKeys.MRC)
            R.id.btnMMinus -> presenter.typeKey(CalculatorKeys.MMINUS)
            R.id.btnMPlus -> presenter.typeKey(CalculatorKeys.MPLUS)
            else -> return
        }
    }

    override fun updateDisplay(value: String) {
        txtDisplay.text = value
    }
}

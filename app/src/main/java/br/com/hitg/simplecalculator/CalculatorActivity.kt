package br.com.hitg.simplecalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {

    val calc = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    fun onClickButton(v: View?) {
        when (v?.id) {
            R.id.btnZero -> calc.typeNumber(0)
            R.id.btnOne -> calc.typeNumber(1)
            R.id.btnTwo -> calc.typeNumber(2)
            R.id.btnThree -> calc.typeNumber(3)
            R.id.btnFour -> calc.typeNumber(4)
            R.id.btnFive -> calc.typeNumber(5)
            R.id.btnSix -> calc.typeNumber(6)
            R.id.btnSeven -> calc.typeNumber(7)
            R.id.btnEight -> calc.typeNumber(8)
            R.id.btnNine -> calc.typeNumber(9)
            R.id.btnDot -> calc.typeDot()
            R.id.btnCE -> calc.ce()
            R.id.btnOff -> System.exit(1)
            R.id.btnBack -> calc.back()
            R.id.btnEquals -> calc.equals()
            R.id.btnPlus -> calc.add()
            R.id.btnMinus -> calc.subtract()
            R.id.btnDivide -> calc.divide()
            R.id.btnMultiply -> calc.multiply()
            R.id.btnPercent -> Toast.makeText(v.context, "btnPercent", Toast.LENGTH_SHORT).show()
            R.id.btnSquareRoot -> Toast.makeText(v.context, "btnSquareRoot", Toast.LENGTH_SHORT).show()
            R.id.btnMRC -> Toast.makeText(v.context, "btnMRC", Toast.LENGTH_SHORT).show()
            R.id.btnMMinus -> Toast.makeText(v.context, "btnMMinus", Toast.LENGTH_SHORT).show()
            R.id.btnMPlus -> Toast.makeText(v.context, "btnMPlus", Toast.LENGTH_SHORT).show()
            else -> return

        }
        txtDisplay.text = calc.displayNumber

    }

}

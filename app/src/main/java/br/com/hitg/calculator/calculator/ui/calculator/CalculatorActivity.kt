package br.com.hitg.calculator.calculator.ui.calculator

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.hitg.calculator.R
import br.com.hitg.calculator.calculator.model.Operations
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View, View.OnClickListener {

    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calculator)

        createPresenter()

        setOnClickListeners()

        configureActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.calc_menu, menu)
        return true
    }

    private fun configureActionBar() {
        title = ""
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,
                android.R.color.transparent)))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.privacy_policy -> {
                val browserIntent = Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.link_privacy_policy)))
                startActivity(browserIntent)
                return true
            }
        }
        return false
    }

    private fun createPresenter() {
        val numberOnDisplay: String
        val currentCalcTotal: String
        val currentOperation: Operations
        val currentNumberInMemory: String
        val isMemoryInUse: Boolean
        val mustCleanDisplayOnNextInteraction: Boolean
        val lastOperation: Operations
        val lastInputValue: String

        // To remove Lint Warning from the sharedPreference getStringMethods. Yes.. I have TOC...
        val defaultZero = "0"
        val defaultNONE = "NONE"

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        numberOnDisplay = sharedPref.getString(CALC_NUMBER_ON_DISPLAY, defaultZero) ?: defaultZero
        currentCalcTotal = sharedPref.getString(CALC_CURRENT_CALC_TOTAL, defaultZero) ?: defaultZero
        currentOperation = Operations.valueOf(sharedPref.getString(
                CALC_CURRENT_OPERATION, defaultNONE) ?: defaultNONE)
        currentNumberInMemory = sharedPref.getString(CALC_NUMBER_IN_MEMORY,
                defaultZero) ?: defaultZero
        isMemoryInUse = sharedPref.getBoolean(CALC_IS_MEMORY_IN_USE, false)
        mustCleanDisplayOnNextInteraction = sharedPref.getBoolean(
                CALC_MUST_CLEAN_DISPLAY, false)
        lastOperation = Operations.valueOf(sharedPref.getString(
                CALC_LAST_OPERATION, defaultNONE) ?: defaultNONE)
        lastInputValue = sharedPref.getString(CALC_LAST_INPUT_VALUE, defaultZero) ?: defaultZero

        presenter = CalculatorPresenter(this)
        presenter.restoreCalculatorState(numberOnDisplay,
                currentCalcTotal,
                currentOperation,
                currentNumberInMemory,
                isMemoryInUse,
                mustCleanDisplayOnNextInteraction,
                lastOperation,
                lastInputValue)
        presenter.start()

    }

    private fun setOnClickListeners() {
        btnZero.setOnClickListener(this)
        btnOne.setOnClickListener(this)
        btnTwo.setOnClickListener(this)
        btnThree.setOnClickListener(this)
        btnFour.setOnClickListener(this)
        btnFive.setOnClickListener(this)
        btnSix.setOnClickListener(this)
        btnSeven.setOnClickListener(this)
        btnEight.setOnClickListener(this)
        btnNine.setOnClickListener(this)
        btnDot.setOnClickListener(this)
        btnCE.setOnClickListener(this)
        btnInvertSignal.setOnClickListener(this)
        btnDel.setOnClickListener(this)
        btnEquals.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnDivide.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnPercent.setOnClickListener(this)
        btnSquareRoot.setOnClickListener(this)
        btnMRC.setOnClickListener(this)
        btnMMinus.setOnClickListener(this)
        btnMPlus.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        try {
            when (view.id) {
                R.id.btnZero -> presenter.buttonZeroClicked()
                R.id.btnOne -> presenter.buttonOneClicked()
                R.id.btnTwo -> presenter.buttonTwoClicked()
                R.id.btnThree -> presenter.buttonThreeClicked()
                R.id.btnFour -> presenter.buttonFourClicked()
                R.id.btnFive -> presenter.buttonFiveClicked()
                R.id.btnSix -> presenter.buttonSixClicked()
                R.id.btnSeven -> presenter.buttonSevenClicked()
                R.id.btnEight -> presenter.buttonEightClicked()
                R.id.btnNine -> presenter.buttonNineClicked()
                R.id.btnDot -> presenter.buttonDotClicked()
                R.id.btnCE -> presenter.buttonCeClicked()
                R.id.btnInvertSignal -> presenter.buttonInvertSignalClicked()
                R.id.btnDel -> presenter.removeLast()
                R.id.btnEquals -> presenter.buttonEqualsClicked()
                R.id.btnPlus -> presenter.buttonAddClicked()
                R.id.btnMinus -> presenter.buttonMinusClicked()
                R.id.btnDivide -> presenter.buttonDivideClicked()
                R.id.btnMultiply -> presenter.buttonMultiplyClicked()
                R.id.btnPercent -> presenter.buttonPercentClicked()
                R.id.btnSquareRoot -> presenter.buttonSquareRootClicked()
                R.id.btnMRC -> presenter.buttonMrcClicked()
                R.id.btnMMinus -> presenter.buttonMMinusClicked()
                R.id.btnMPlus -> presenter.buttonMPlusClicked()
                else -> return
            }
        } catch (ex: Exception) {
            val crashlytics = FirebaseCrashlytics.getInstance()
            val message = ex.message
            if (message != null) {
                crashlytics.log(message)
            }
            showError()
            presenter = CalculatorPresenter(this)
        }
    }

    private fun showError() {
        txtDisplay.setText(R.string.error)
        updateOperation(Operations.NONE)
        updateMemoryDisplay(false, "")
        persistState()
    }

    override fun updateDisplay(value: String) {
        txtDisplay.text = value
        txtDisplay.announceForAccessibility(txtDisplay.text)
        if (txtMemory.text == "") {
            txtMemoryDisplay.announceForAccessibility(resources.getString(R.string.cont_desc_memory_value))
            txtMemoryDisplay.announceForAccessibility(txtMemoryDisplay.text)
        }
        persistState()
    }

    override fun updateOperation(currentOperation: Operations) {
        when (currentOperation) {
            Operations.NONE -> txtSignals.text = ""
            Operations.ADDITION -> txtSignals.setText(R.string.plus_sign)
            Operations.SUBTRACTION -> txtSignals.setText(R.string.minus_sign)
            Operations.MULTIPLICATION -> txtSignals.setText(R.string.multiplication_sign)
            Operations.DIVISION -> txtSignals.setText(R.string.division_sign)
        }
        persistState()
    }

    override fun updateMemoryDisplay(isMemoryInUse: Boolean, value: String) {
        if (isMemoryInUse) {
            txtMemory.setText(R.string.memory_symbol)
            txtMemoryDisplay.text = value
            txtMemoryDisplay.announceForAccessibility(resources.getString(R.string.cont_desc_memory_value))
            txtMemoryDisplay.announceForAccessibility(txtMemoryDisplay.text)
        } else {
            txtMemory.text = ""
            txtMemoryDisplay.text = ""
        }
        persistState()
    }


    private fun persistState() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(CALC_NUMBER_ON_DISPLAY, presenter.currentDisplayValue)
            putString(CALC_CURRENT_CALC_TOTAL, presenter.currentCalcTotal)
            putString(CALC_CURRENT_OPERATION, presenter.currentOperation.name)
            putString(CALC_NUMBER_IN_MEMORY, presenter.currentNumberInMemory)
            putBoolean(CALC_IS_MEMORY_IN_USE, presenter.isMemoryInUse)
            putBoolean(CALC_MUST_CLEAN_DISPLAY, presenter.mustCleanDisplayOnNextInteraction)
            putString(CALC_LAST_OPERATION, presenter.lastOperation.name)
            putString(CALC_LAST_INPUT_VALUE, presenter.lastInputValue)
            apply()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the state so that next time we know if we need to refresh data.
        super.onSaveInstanceState(outState.apply {
            putString(CALC_NUMBER_ON_DISPLAY, presenter.currentDisplayValue)
            putString(CALC_CURRENT_CALC_TOTAL, presenter.currentCalcTotal)
            putString(CALC_CURRENT_OPERATION, presenter.currentOperation.name)
            putString(CALC_NUMBER_IN_MEMORY, presenter.currentNumberInMemory)
            putBoolean(CALC_IS_MEMORY_IN_USE, presenter.isMemoryInUse)
            putBoolean(CALC_MUST_CLEAN_DISPLAY, presenter.mustCleanDisplayOnNextInteraction)
            putString(CALC_LAST_OPERATION, presenter.lastOperation.name)
            putString(CALC_LAST_INPUT_VALUE, presenter.lastInputValue)
        })
    }

    companion object {
        const val CALC_NUMBER_ON_DISPLAY = "CALC_NUMBER_ON_DISPLAY"
        const val CALC_CURRENT_CALC_TOTAL = "CALC_CURRENT_CALC_TOTAL"
        const val CALC_CURRENT_OPERATION = "CALC_CURRENT_OPERATION"
        const val CALC_NUMBER_IN_MEMORY = "CALC_NUMBER_IN_MEMORY"
        const val CALC_IS_MEMORY_IN_USE = "CALC_IS_MEMORY_IN_USE"
        const val CALC_MUST_CLEAN_DISPLAY = "CALC_MUST_CLEAN_DISPLAY"
        const val CALC_LAST_OPERATION = "CALC_LAST_OPERATION"
        const val CALC_LAST_INPUT_VALUE = "CALC_LAST_INPUT_VALUE"
    }
}

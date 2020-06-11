package br.com.hitg.calculator.calculator

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
import br.com.hitg.calculator.calculator.CalculatorContract.CalculatorNumbers
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View, View.OnClickListener {

    /**
     * Presenter
     */
    override lateinit var presenter: CalculatorContract.Presenter

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(android.net.Uri , String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
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

    /**
     * Creates the presenter and recover the state if it needs.
     */
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

    /**
     * Set onClickListeners for all controls.
     */
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

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    override fun onClick(view: View) {
        try {
            when (view.id) {
                R.id.btnZero         -> presenter.typeNumber(CalculatorNumbers.ZERO)
                R.id.btnOne          -> presenter.typeNumber(CalculatorNumbers.ONE)
                R.id.btnTwo          -> presenter.typeNumber(CalculatorNumbers.TWO)
                R.id.btnThree        -> presenter.typeNumber(CalculatorNumbers.THREE)
                R.id.btnFour         -> presenter.typeNumber(CalculatorNumbers.FOUR)
                R.id.btnFive         -> presenter.typeNumber(CalculatorNumbers.FIVE)
                R.id.btnSix          -> presenter.typeNumber(CalculatorNumbers.SIX)
                R.id.btnSeven        -> presenter.typeNumber(CalculatorNumbers.SEVEN)
                R.id.btnEight        -> presenter.typeNumber(CalculatorNumbers.EIGHT)
                R.id.btnNine         -> presenter.typeNumber(CalculatorNumbers.NINE)
                R.id.btnDot          -> presenter.typeDot()
                R.id.btnCE           -> presenter.ce()
                R.id.btnInvertSignal -> presenter.invertSignal()
                R.id.btnDel          -> presenter.removeLast()
                R.id.btnEquals       -> presenter.typeEquals()
                R.id.btnPlus         -> presenter.add()
                R.id.btnMinus        -> presenter.minus()
                R.id.btnDivide       -> presenter.divide()
                R.id.btnMultiply     -> presenter.multiply()
                R.id.btnPercent      -> presenter.percent()
                R.id.btnSquareRoot   -> presenter.squareRoot()
                R.id.btnMRC          -> presenter.memoryResultAndClean()
                R.id.btnMMinus       -> presenter.memorySubtract()
                R.id.btnMPlus        -> presenter.memoryAdd()
                else                 -> return
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

    /**
     * Updates the display with a default message to indicate Error!
     */
    private fun showError() {
        txtDisplay.setText(R.string.error)
        updateOperation(Operations.NONE)
        updateMemoryDisplay(false, "")
        persistState()
    }

    /**
     * Updates the display.
     *
     * @param value Value to display.
     */
    override fun updateDisplay(value: String) {
        txtDisplay.text = value
        txtDisplay.announceForAccessibility(txtDisplay.text)
        if (txtMemory.text == "") {
            txtMemoryDisplay.announceForAccessibility(resources.getString(R.string.cont_desc_memory_value))
            txtMemoryDisplay.announceForAccessibility(txtMemoryDisplay.text)
        }
        persistState()
    }

    /**
     * Updates the operation.
     *
     * @param currentOperation Operation to display.
     */
    override fun updateOperation(currentOperation: Operations) {
        when (currentOperation) {
            Operations.NONE           -> txtSignals.text = ""
            Operations.ADDITION       -> txtSignals.setText(R.string.plus_sign)
            Operations.SUBTRACTION    -> txtSignals.setText(R.string.minus_sign)
            Operations.MULTIPLICATION -> txtSignals.setText(R.string.multiplication_sign)
            Operations.DIVISION       -> txtSignals.setText(R.string.division_sign)
        }
        persistState()
    }

    /**
     * Update the display of the User Memory Calculator.
     *
     * @param isMemoryInUse Whether must show memory or not
     * @param value Value to show.
     */
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

    /**
     * Called to retrieve per-instance state from an activity before being killed
     * so that the state can be restored in {@link #onCreate} or
     * {@link #onRestoreInstanceState} (the {@link Bundle} populated by this method
     * will be passed to both).
     *
     * @param outState Bundle in which to place your saved state.
     *
     * @see #onCreate
     * @see #onRestoreInstanceState
     * @see #onPause
     */
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

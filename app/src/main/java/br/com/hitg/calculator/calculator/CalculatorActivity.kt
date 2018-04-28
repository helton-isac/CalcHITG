package br.com.hitg.calculator.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.hitg.calculator.R
import br.com.hitg.calculator.calculator.CalculatorContract.CalculatorNumbers
import com.crashlytics.android.Crashlytics
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

        createPresenter(savedInstanceState)

        setOnClickListeners()
    }

    /**
     * Creates the presenter and recover the state if it needs.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    private fun createPresenter(savedInstanceState: Bundle?) {
        presenter = CalculatorPresenter(this)

        if (savedInstanceState != null) {

            val numberOnDisplay = savedInstanceState.getString(CALC_NUMBER_ON_DISPLAY)
            val currentCalcTOtal = savedInstanceState.getString(CALC_CURRENT_CALC_TOTAL)
            val currentOperation: Operations = Operations.valueOf(savedInstanceState.getString(
                    CALC_CURRENT_OPERATION))
            val currentNumberInMemory = savedInstanceState.getString(CALC_NUMBER_IN_MEMORY)
            val isMemoryInUse = savedInstanceState.getBoolean(CALC_IS_MEMORY_IN_USE)
            val mustcleanDisplayOnNextInteraction = savedInstanceState.getBoolean(
                    CALC_MUST_CLEAN_DISPLAY)
            val lastOperation: Operations = Operations.valueOf(savedInstanceState.getString(
                    CALC_LAST_OPERATION))
            val lastInputValue: String = savedInstanceState.getString(CALC_LAST_INPUT_VALUE)

            presenter.restoreCalculatorState(numberOnDisplay,
                    currentCalcTOtal,
                    currentOperation,
                    currentNumberInMemory,
                    isMemoryInUse,
                    mustcleanDisplayOnNextInteraction,
                    lastOperation,
                    lastInputValue)
        }
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
        btnOff.setOnClickListener(this)
        btnDell.setOnClickListener(this)
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
                R.id.btnZero       -> presenter.typeNumber(CalculatorNumbers.ZERO)
                R.id.btnOne        -> presenter.typeNumber(CalculatorNumbers.ONE)
                R.id.btnTwo        -> presenter.typeNumber(CalculatorNumbers.TWO)
                R.id.btnThree      -> presenter.typeNumber(CalculatorNumbers.THREE)
                R.id.btnFour       -> presenter.typeNumber(CalculatorNumbers.FOUR)
                R.id.btnFive       -> presenter.typeNumber(CalculatorNumbers.FIVE)
                R.id.btnSix        -> presenter.typeNumber(CalculatorNumbers.SIX)
                R.id.btnSeven      -> presenter.typeNumber(CalculatorNumbers.SEVEN)
                R.id.btnEight      -> presenter.typeNumber(CalculatorNumbers.EIGHT)
                R.id.btnNine       -> presenter.typeNumber(CalculatorNumbers.NINE)
                R.id.btnDot        -> presenter.typeDot()
                R.id.btnCE         -> presenter.ce()
                R.id.btnOff        -> presenter.exit()
                R.id.btnDell       -> presenter.removeLast()
                R.id.btnEquals     -> presenter.typeEquals()
                R.id.btnPlus       -> presenter.add()
                R.id.btnMinus      -> presenter.minus()
                R.id.btnDivide     -> presenter.divide()
                R.id.btnMultiply   -> presenter.multiply()
                R.id.btnPercent    -> presenter.percent()
                R.id.btnSquareRoot -> presenter.squareRoot()
                R.id.btnMRC        -> presenter.memoryResultAndClean()
                R.id.btnMMinus     -> presenter.memorySubtract()
                R.id.btnMPlus      -> presenter.memoryAdd()
                else               -> return
            }
        } catch (ex: Exception) {
            showError()
            presenter = CalculatorPresenter(this)
            Crashlytics.logException(ex);
        }
    }

    private fun showError() {
        txtDisplay.setText(R.string.error)
        updateOperation(Operations.NONE)
        showMemoryIndicator(false)
    }

    /**
     * Updates the display.
     *
     * @param value Value to display.
     */
    override fun updateDisplay(value: String) {
        txtDisplay.text = value
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
    }

    /**
     * Shows the indicator if there is a number in memory or not.
     *
     * @param isMemoryInUse True if must shows the indicator.
     */
    override fun showMemoryIndicator(isMemoryInUse: Boolean) {
        txtMemory.visibility = if (isMemoryInUse) View.VISIBLE else View.GONE
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
            putBoolean(CALC_MUST_CLEAN_DISPLAY, presenter.mustcleanDisplayOnNextInteraction)
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

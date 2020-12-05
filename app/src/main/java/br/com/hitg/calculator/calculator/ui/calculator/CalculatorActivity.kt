package br.com.hitg.calculator.calculator.ui.calculator

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.hitg.calculator.R
import br.com.hitg.domain.model.Operations
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity(), CalculatorContract.View {

    override lateinit var presenter: CalculatorContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_calculator)

        createPresenter()

        setOnClickListeners()

        hideActionBar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.calc_menu, menu)
        return true
    }

    private fun hideActionBar() {
        title = ""
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,
                android.R.color.transparent)))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.privacy_policy -> {
                showPrivacyPolicy()
                return true
            }
        }
        return false
    }

    private fun showPrivacyPolicy() {
        val browserIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.link_privacy_policy)))
        startActivity(browserIntent)
    }

    private fun createPresenter() {
        presenter = CalculatorPresenter(this, this)
        presenter.start()
    }

    private fun setOnClickListeners() {
        btnZero.setOnClickListener { execMethod(presenter::buttonZeroClicked) }
        btnOne.setOnClickListener { execMethod(presenter::buttonOneClicked) }
        btnTwo.setOnClickListener { execMethod(presenter::buttonTwoClicked) }
        btnThree.setOnClickListener { execMethod(presenter::buttonThreeClicked) }
        btnFour.setOnClickListener { execMethod(presenter::buttonFourClicked) }
        btnFive.setOnClickListener { execMethod(presenter::buttonFiveClicked) }
        btnSix.setOnClickListener { execMethod(presenter::buttonSixClicked) }
        btnSeven.setOnClickListener { execMethod(presenter::buttonSevenClicked) }
        btnEight.setOnClickListener { execMethod(presenter::buttonEightClicked) }
        btnNine.setOnClickListener { execMethod(presenter::buttonNineClicked) }
        btnDot.setOnClickListener { execMethod(presenter::buttonDotClicked) }
        btnCE.setOnClickListener { execMethod(presenter::buttonCeClicked) }
        btnInvertSignal.setOnClickListener { execMethod(presenter::buttonInvertSignalClicked) }
        btnDel.setOnClickListener { execMethod(presenter::buttonDelClicked) }
        btnEquals.setOnClickListener { execMethod(presenter::buttonEqualsClicked) }
        btnPlus.setOnClickListener { execMethod(presenter::buttonAddClicked) }
        btnMinus.setOnClickListener { execMethod(presenter::buttonMinusClicked) }
        btnDivide.setOnClickListener { execMethod(presenter::buttonDivideClicked) }
        btnMultiply.setOnClickListener { execMethod(presenter::buttonMultiplyClicked) }
        btnPercent.setOnClickListener { execMethod(presenter::buttonPercentClicked) }
        btnSquareRoot.setOnClickListener { execMethod(presenter::buttonSquareRootClicked) }
        btnMRC.setOnClickListener { execMethod(presenter::buttonMrcClicked) }
        btnMMinus.setOnClickListener { execMethod(presenter::buttonMMinusClicked) }
        btnMPlus.setOnClickListener { execMethod(presenter::buttonMPlusClicked) }
    }

    private fun execMethod(method: () -> Unit) {
        try {
            method()
        } catch (ex: ArithmeticException) {
            showError()
        } catch (ex: Exception) {
            showError()
            FirebaseCrashlytics.getInstance().recordException(ex)
        }
    }

    private fun showError() {
        txtDisplay.setText(R.string.error)
        showOperation(Operations.NONE)
    }

    override fun updateDisplay(value: String) {
        txtDisplay.text = value
        txtDisplay.announceForAccessibility(txtDisplay.text)
        if (txtMemory.text == "") {
            txtMemoryDisplay.announceForAccessibility(resources.getString(R.string.cont_desc_memory_value))
            txtMemoryDisplay.announceForAccessibility(txtMemoryDisplay.text)
        }
    }

    override fun showOperation(currentOperation: Operations) {
        when (currentOperation) {
            Operations.NONE -> txtSignals.text = ""
            Operations.ADDITION -> txtSignals.setText(R.string.plus_sign)
            Operations.SUBTRACTION -> txtSignals.setText(R.string.minus_sign)
            Operations.MULTIPLICATION -> txtSignals.setText(R.string.multiplication_sign)
            Operations.DIVISION -> txtSignals.setText(R.string.division_sign)
        }
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
    }
}

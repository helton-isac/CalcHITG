package com.hitg.data.local.database

import android.content.SharedPreferences
import com.hitg.data.local.model.CalculatorState

class CalculatorStateSharedPrefImpl(
        private val sharedPref: SharedPreferences
) : CalculatorStateDAO {

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

    override fun getCalculatorState(): CalculatorState {
        val defaultZero = "0"
        val defaultNONE = "NONE"

        val displayValue =
                sharedPref.getString(CALC_NUMBER_ON_DISPLAY, defaultZero) ?: defaultZero
        val calcTotal =
                sharedPref.getString(CALC_CURRENT_CALC_TOTAL, defaultZero) ?: defaultZero
        val currentOperation =
                sharedPref.getString(CALC_CURRENT_OPERATION, defaultNONE) ?: defaultNONE
        val numberInMemory =
                sharedPref.getString(CALC_NUMBER_IN_MEMORY, defaultZero) ?: defaultZero
        val isMemoryInUse: Boolean =
                sharedPref.getBoolean(CALC_IS_MEMORY_IN_USE, false)
        val mustCleanDisplayOnNextInteraction: Boolean =
                sharedPref.getBoolean(CALC_MUST_CLEAN_DISPLAY, false)
        val lastOperation =
                sharedPref.getString(CALC_LAST_OPERATION, defaultNONE) ?: defaultNONE
        val lastInputValue =
                sharedPref.getString(CALC_LAST_INPUT_VALUE, defaultZero) ?: defaultZero

        return CalculatorState(
                displayValue = displayValue,
                calcTotal = calcTotal,
                currentOperation = currentOperation,
                numberInMemory = numberInMemory,
                isMemoryInUse = isMemoryInUse,
                mustCleanDisplayOnNextInteraction = mustCleanDisplayOnNextInteraction,
                lastOperation = lastOperation,
                lastInputValue = lastInputValue)
    }

    override fun persistCalculatorState(calculatorState: CalculatorState) {
        with(sharedPref.edit()) {
            putString(CALC_NUMBER_ON_DISPLAY, calculatorState.displayValue)
            putString(CALC_CURRENT_CALC_TOTAL, calculatorState.calcTotal)
            putString(CALC_CURRENT_OPERATION, calculatorState.currentOperation)
            putString(CALC_NUMBER_IN_MEMORY, calculatorState.numberInMemory)
            putBoolean(CALC_IS_MEMORY_IN_USE, calculatorState.isMemoryInUse)
            putBoolean(CALC_MUST_CLEAN_DISPLAY, calculatorState.mustCleanDisplayOnNextInteraction)
            putString(CALC_LAST_OPERATION, calculatorState.lastOperation)
            putString(CALC_LAST_INPUT_VALUE, calculatorState.lastInputValue)
            apply()
        }
    }
}
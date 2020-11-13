package com.hitg.data.local.database

import com.hitg.data.local.model.CalculatorState

interface CalculatorStateDAO {

    fun getCalculatorState(): CalculatorState
    fun persistCalculatorState(calculatorState: CalculatorState)
}
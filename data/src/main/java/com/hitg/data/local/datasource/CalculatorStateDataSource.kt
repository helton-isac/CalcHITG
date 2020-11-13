package com.hitg.data.local.datasource

import com.hitg.data.local.model.CalculatorState

interface CalculatorStateDataSource {

    fun getCalculatorState(): CalculatorState
    fun persistCalculatorState(calculatorState: CalculatorState)
}
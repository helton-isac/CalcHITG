package com.hitg.data.local.database

import br.com.hitg.domain.model.ICalculatorState

interface CalculatorStateDAO {

    fun getCalculatorState(): ICalculatorState
    fun persistCalculatorState(calculatorState: ICalculatorState)
}
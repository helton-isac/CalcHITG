package com.hitg.data.local.datasource

import br.com.hitg.domain.model.ICalculatorState

interface CalculatorStateDataSource {

    fun getCalculatorState(): ICalculatorState
    fun persistCalculatorState(calculatorState: ICalculatorState)
}
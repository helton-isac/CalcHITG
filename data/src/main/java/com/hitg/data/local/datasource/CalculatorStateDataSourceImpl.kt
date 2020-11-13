package com.hitg.data.local.datasource

import com.hitg.data.local.database.CalculatorStateDAO
import com.hitg.data.local.model.CalculatorState

class CalculatorStateDataSourceImpl(
        private val dao: CalculatorStateDAO
) : CalculatorStateDataSource {

    override fun getCalculatorState(): CalculatorState {
        return dao.getCalculatorState()
    }

    override fun persistCalculatorState(calculatorState: CalculatorState) {
        dao.persistCalculatorState(calculatorState)
    }
}
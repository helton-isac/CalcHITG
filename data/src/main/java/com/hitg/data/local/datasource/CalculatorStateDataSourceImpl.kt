package com.hitg.data.local.datasource

import br.com.hitg.domain.model.ICalculatorState
import com.hitg.data.local.database.CalculatorStateDAO

class CalculatorStateDataSourceImpl(
        private val dao: CalculatorStateDAO
) : CalculatorStateDataSource {

    override fun getCalculatorState(): ICalculatorState {
        return dao.getCalculatorState()
    }

    override fun persistCalculatorState(calculatorState: ICalculatorState) {
        dao.persistCalculatorState(calculatorState)
    }
}
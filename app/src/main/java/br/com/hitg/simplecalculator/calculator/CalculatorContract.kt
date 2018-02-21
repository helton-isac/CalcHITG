package br.com.hitg.simplecalculator.calculator

import br.com.hitg.simplecalculator.BasePresenter
import br.com.hitg.simplecalculator.BaseView

/**
 * Created by Helton on 20/02/2018.
 */
interface CalculatorContract {
    interface View : BaseView<Presenter> {
        fun updateDisplay(value: String)
    }

    interface Presenter : BasePresenter {

        var currentOperation: Operations?

        fun typeKey(key: CalculatorKeys)
    }
}
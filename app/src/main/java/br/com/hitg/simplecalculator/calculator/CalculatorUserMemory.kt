package br.com.hitg.simplecalculator.calculator

import java.math.BigDecimal

/**
 * Created by Helton on 24/02/2018.
 */
class CalculatorUserMemory {

    private var memory: BigDecimal = BigDecimal(0)

    var isMemoryInUse: Boolean = false

    /**
     * Returns the current memory and reset
     */
    fun mrc(): BigDecimal {
        try {
            return memory
        } finally {
            isMemoryInUse = false
            memory = BigDecimal(0)
        }
    }

    /**
     * Increase a value in the internal memory.
     */
    fun mPlus(value: Double) {
        isMemoryInUse = true
        memory = memory.add(BigDecimal.valueOf(value))
    }

    /**
     * Decrease a value from the internal memory.
     */
    fun mSubtract(value: Double) {
        isMemoryInUse = true
        memory = memory.subtract(BigDecimal.valueOf(value))
    }
}
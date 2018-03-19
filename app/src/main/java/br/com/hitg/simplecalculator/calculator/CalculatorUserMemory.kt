package br.com.hitg.simplecalculator.calculator

import java.math.BigDecimal

/**
 * User Memory
 */
class CalculatorUserMemory {

    /**
     * Internal user memory.
     */
    private var memory: BigDecimal = BigDecimal("0")

    /**
     * True if user is using the memory
     */
    var isMemoryInUse: Boolean = false
        private set

    /**
     * Returns the current memory and reset
     */
    fun mrc(): BigDecimal {
        return try {
            memory
        } finally {
            isMemoryInUse = false
            memory = BigDecimal("0")
        }
    }

    /**
     * Returns the current memory
     */
    fun mr(): BigDecimal {
        return memory
    }

    /**
     * Increase a value in the internal memory.
     */
    fun mPlus(value: BigDecimal) {
        isMemoryInUse = true
        memory = memory.add(value)
    }

    /**
     * Decrease a value from the internal memory.
     */
    fun mSubtract(value: BigDecimal) {
        isMemoryInUse = true
        memory = memory.subtract(value)
    }
}
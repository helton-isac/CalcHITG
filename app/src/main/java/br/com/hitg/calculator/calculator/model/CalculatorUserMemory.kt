package br.com.hitg.calculator.calculator.model

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
            resetMemory()
        }
    }

    /**
     * Clean the current memory.
     */
    private fun resetMemory() {
        isMemoryInUse = false
        memory = BigDecimal("0")
    }

    /**
     * Returns the current memory
     */
    fun currentMemoryValue(): BigDecimal {
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

    /**
     * Restore the memory status
     * @param currentNumberInMemory number to save in memory
     * @param isMemoryInUse in case the memory value is 0
     */
    fun restoreMemoryStatus(currentNumberInMemory: String, isMemoryInUse: Boolean) {
        if (isMemoryInUse) {
            try {
                memory = BigDecimal(currentNumberInMemory)
                this.isMemoryInUse = isMemoryInUse
            } catch (ignored: Exception) {
                resetMemory()
            }
        }
    }
}
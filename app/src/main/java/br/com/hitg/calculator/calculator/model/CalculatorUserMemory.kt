package br.com.hitg.calculator.calculator.model

import java.math.BigDecimal

class CalculatorUserMemory {

    private var memory: BigDecimal = BigDecimal("0")

    var isMemoryInUse: Boolean = false
        private set

    fun mrc(): BigDecimal {
        return try {
            memory
        } finally {
            resetMemory()
        }
    }

    private fun resetMemory() {
        isMemoryInUse = false
        memory = BigDecimal("0")
    }

    fun currentMemoryValue(): BigDecimal {
        return memory
    }

    fun mPlus(value: BigDecimal) {
        isMemoryInUse = true
        memory = memory.add(value)
    }

    fun mSubtract(value: BigDecimal) {
        isMemoryInUse = true
        memory = memory.subtract(value)
    }

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
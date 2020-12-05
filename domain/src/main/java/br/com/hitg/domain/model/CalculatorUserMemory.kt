package br.com.hitg.domain.model

import java.math.BigDecimal

class CalculatorUserMemory {

    private var valueInMemory: BigDecimal = BigDecimal("0")

    var isMemoryInUse: Boolean = false
        private set

    fun mrc(): BigDecimal {
        return try {
            valueInMemory
        } finally {
            resetMemory()
        }
    }

    private fun resetMemory() {
        isMemoryInUse = false
        valueInMemory = BigDecimal("0")
    }

    fun currentMemoryValue(): BigDecimal {
        return valueInMemory
    }

    fun mPlus(value: BigDecimal) {
        isMemoryInUse = true
        valueInMemory = valueInMemory.add(value)
    }

    fun mSubtract(value: BigDecimal) {
        isMemoryInUse = true
        valueInMemory = valueInMemory.subtract(value)
    }

    fun restoreMemoryStatus(currentNumberInMemory: String, isMemoryInUse: Boolean) {
        if (isMemoryInUse) {
            try {
                valueInMemory = BigDecimal(currentNumberInMemory)
                this.isMemoryInUse = isMemoryInUse
            } catch (ignored: Exception) {
                resetMemory()
            }
        }
    }
}
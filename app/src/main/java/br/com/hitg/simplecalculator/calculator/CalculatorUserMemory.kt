package br.com.hitg.simplecalculator.calculator

/**
 * Created by Helton on 24/02/2018.
 */
class CalculatorUserMemory {

    private var memory: Double = 0.0

    var isMemoryInUse: Boolean = false

    /**
     * Returns the current memory and reset
     */
    fun mrc(): Double {
        try {
            return memory
        } finally {
            isMemoryInUse = false
            memory = 0.0
        }
    }

    /**
     * Increase a value in the internal memory.
     */
    fun mPlus(value: Double) {
        isMemoryInUse = true
        memory += value
    }

    /**
     * Decrease a value from the internal memory.
     */
    fun mSubtract(value: Double) {
        isMemoryInUse = true
        memory -= value
    }
}
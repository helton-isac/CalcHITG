package com.hitg.data.local.model

import br.com.hitg.domain.model.ICalculatorState

data class CalculatorState(
        override val displayValue: String,
        override val calcTotal: String,
        override val currentOperation: String,
        override val numberInMemory: String,
        override val isMemoryInUse: Boolean,
        override val mustCleanDisplayOnNextInteraction: Boolean,
        override val lastOperation: String,
        override val lastInputValue: String,
) : ICalculatorState
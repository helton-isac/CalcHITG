package br.com.hitg.domain.model

interface ICalculatorState {

    val displayValue: String
    val calcTotal: String
    val currentOperation: String
    val numberInMemory: String
    val isMemoryInUse: Boolean
    val mustCleanDisplayOnNextInteraction: Boolean
    val lastOperation: String
    val lastInputValue: String
}
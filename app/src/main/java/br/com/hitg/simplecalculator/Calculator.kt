package br.com.hitg.simplecalculator

/**
 * Created by Helton on 17/02/2018.
 */

enum class Operation {
    ADDITION,
    SUBTRACTION,
    DIVISION,
    MULTIPLICATION,
    NONE
}

class Calculator {

    var memory: Double = 0.0

    var calcMemory: Int = 0

    var displayNumber: String = "0"

    var hasDecimals: Boolean = false

    var currentOperation = Operation.NONE

    var cleanDisplay: Boolean = false

    fun add() {
        if (currentOperation == Operation.NONE) {
            memory += displayNumber.toDouble()
            currentOperation = Operation.ADDITION
            displayNumber = displayNumber + "+"
            cleanDisplay = true;
        } else {
            currentOperation == Operation.NONE
            back()
            add()
        }
    }

    fun subtract() {
        if (currentOperation == Operation.NONE) {
            memory += displayNumber.toDouble()
            currentOperation = Operation.SUBTRACTION
            displayNumber = displayNumber + "-"
            cleanDisplay = true;
        } else {
            currentOperation == Operation.NONE
            back()
            subtract()
        }
    }

    fun multiply() {
        if (currentOperation == Operation.NONE) {
            memory += displayNumber.toDouble()
            currentOperation = Operation.MULTIPLICATION
            displayNumber = displayNumber + "×"
            cleanDisplay = true;
        } else {
            currentOperation == Operation.NONE
            back()
            multiply()
        }
    }

    fun divide() {
        if (currentOperation == Operation.NONE) {
            memory += displayNumber.toDouble()
            currentOperation = Operation.DIVISION
            displayNumber = displayNumber + "÷"
            cleanDisplay = true;
        } else {
            currentOperation == Operation.NONE
            back()
            divide()
        }
    }

    fun equals() {
        var tempValue = displayNumber.toDouble()
        var result: Double
        when (currentOperation) {
            Operation.NONE -> return
            Operation.ADDITION -> result = memory + tempValue
            Operation.SUBTRACTION -> result = memory - tempValue
            Operation.DIVISION -> result = memory / tempValue
            Operation.MULTIPLICATION -> result = memory * tempValue
        }
        memory = 0.0;
        currentOperation = Operation.NONE
        cleanDisplay = true
        displayValue(result)
    }

    fun displayValue(value:Double){
        if(hasDecimals || value % 1 != 0.0){
            displayNumber = value.toString()
        }else{
            displayNumber = value.toLong().toString()
        }

    }

    fun typeNumber(value: Int) {
        if (cleanDisplay) {
            cleanDisplay = false;
            displayNumber = value.toString()
        } else {
            displayNumber = if (displayNumber == "0") value.toString() else displayNumber + value.toString()
        }
    }

    fun type(value: String) {
        displayNumber = displayNumber + value
    }

    fun typeDot() {
        if (!hasDecimals) {
            hasDecimals = true
            type(".")
        }
    }


    fun ce() {
        memory = 0.0;
        displayNumber = "0"
    }

    fun back() {
        if (displayNumber.length > 0) displayNumber = displayNumber.substring(0, displayNumber.length - 1)
        if (displayNumber == "") displayNumber = "0"

    }


}
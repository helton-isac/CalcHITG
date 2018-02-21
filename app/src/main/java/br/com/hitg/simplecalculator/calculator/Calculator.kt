package br.com.hitg.simplecalculator.calculator

/**
 * Created by Helton on 20/02/2018.
 *
 * Model of the Calculator
 */
class Calculator {
    var displayNumber: String = "0"

    var tempMemory: Double = 0.0

    var hasDecimals: Boolean = false

    var currentOperation: Operations? = null

    fun typeNumber(number: Int) {
        if(displayNumber == "0"){
            displayNumber = number.toString()
        }else {
            displayNumber += number.toString()
        }
    }
//
//    fun add() {
//        if (currentOperation == null) {
//            memory += displayNumber.toDouble()
//            currentOperation = Operations.ADDITION
//            displayNumber = displayNumber + "+"
//            cleanDisplay = true;
//        } else {
//            currentOperation == null
//            back()
//            add()
//        }
//    }
//
//
//
//    fun subtract() {
//        if (currentOperation == null) {
//            memory += displayNumber.toDouble()
//            currentOperation = Operations.SUBTRACTION
//            displayNumber = displayNumber + "-"
//            cleanDisplay = true;
//        } else {
//            currentOperation == null
//            back()
//            subtract()
//        }
//    }
//
//    fun multiply() {
//        if (currentOperation == null) {
//            memory += displayNumber.toDouble()
//            currentOperation = Operations.MULTIPLICATION
//            displayNumber = displayNumber + "×"
//            cleanDisplay = true;
//        } else {
//            currentOperation == null
//            back()
//            multiply()
//        }
//    }
//
//    fun divide() {
//        if (currentOperation == null) {
//            memory += displayNumber.toDouble()
//            currentOperation = Operations.DIVISION
//            displayNumber = displayNumber + "÷"
//            cleanDisplay = true;
//        } else {
//            currentOperation == null
//            back()
//            divide()
//        }
//    }
//
//    fun equals() {
//        var tempValue = displayNumber.toDouble()
//        var result: Double
//        when (currentOperation) {
//            null -> return
//            Operations.ADDITION -> result = memory + tempValue
//            Operations.SUBTRACTION -> result = memory - tempValue
//            Operations.DIVISION -> result = memory / tempValue
//            Operations.MULTIPLICATION -> result = memory * tempValue
//        }
//        memory = 0.0;
//        currentOperation = null
//        cleanDisplay = true
//        displayValue(result)
//    }
//
//    fun displayValue(value:Double){
//        if(hasDecimals || value % 1 != 0.0){
//            displayNumber = value.toString()
//        }else{
//            displayNumber = value.toLong().toString()
//        }
//
//    }
//
//    fun typeNumber(value: Int) {
//        if (cleanDisplay) {
//            cleanDisplay = false;
//            displayNumber = value.toString()
//        } else {
//            displayNumber = if (displayNumber == "0") value.toString() else displayNumber + value.toString()
//        }
//    }
//
//    fun type(value: String) {
//        displayNumber = displayNumber + value
//    }
//
//    fun typeDot() {
//        if (!hasDecimals) {
//            hasDecimals = true
//            type(".")
//        }
//    }
//
//
//    fun ce() {
//        memory = 0.0;
//        displayNumber = "0"
//    }
//
//    fun back() {
//        if (displayNumber.length > 0) displayNumber = displayNumber.substring(0, displayNumber.length - 1)
//        if (displayNumber == "") displayNumber = "0"
//
//    }
}
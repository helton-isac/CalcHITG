package br.com.hitg.calculator.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Helton on 20/03/2018.
 */
class CalculatorTest {
    @Test
    fun checkEnkoTest() {
        var calc = Calculator()

        /**
         * ON/CE = 0
         */
        assertEquals("0", calc.displayNumber.toString())

        // Math: (12 + 34) × 5 ÷ 2 = 115
        // Operations: 12 + 34 × 5 ÷ 2 =
        typeNumberKeyByKey("12", calc)
        calc.add()
        typeNumberKeyByKey("34", calc)
        calc.multiply()
        typeNumberKeyByKey("5", calc)
        calc.divide()
        typeNumberKeyByKey("2", calc)
        calc.equals()
        assertEquals("115", calc.displayNumber.toString())

        // Math: 273 + [572] = 845
        // Operations: 273 + 572 =
        typeNumberKeyByKey("273", calc)
        calc.add()
        typeNumberKeyByKey("572", calc)
        calc.equals()
        assertEquals("845", calc.displayNumber.toString())

        // Math: 768 + [572] = 1340
        // Operations: 768 =
        typeNumberKeyByKey("768", calc)
        calc.equals()
        assertEquals("1340", calc.displayNumber.toString())


        // Math: 597 - [184] = 413
        // Operations: 597 - 184 =
        typeNumberKeyByKey("597", calc)
        calc.subtract()
        typeNumberKeyByKey("184", calc)
        calc.equals()
        assertEquals("413", calc.displayNumber.toString())


        // Math: 323 - [184] = 139
        // Operations: 323 =
        typeNumberKeyByKey("323", calc)
        calc.equals()
        assertEquals("139", calc.displayNumber.toString())

        // Math: 295 × [8] = 2360
        // Operations: 295 × 8 =
        typeNumberKeyByKey("295", calc)
        calc.multiply()
        typeNumberKeyByKey("8", calc)
        calc.equals()
        assertEquals("2360", calc.displayNumber.toString())

        // Math: 6×[8]= 48
        // Operations: 6 =
        typeNumberKeyByKey("6", calc)
        calc.equals()
        assertEquals("48", calc.displayNumber.toString())

        // Math: 759 ÷ [23] = 33
        // Operations: 759 ÷ 23 =
        typeNumberKeyByKey("759", calc)
        calc.divide()
        typeNumberKeyByKey("23", calc)
        calc.equals()
        assertEquals("33", calc.displayNumber.toString())

        // Math: 437 ÷ [23] = 19
        // Operations: 437 =
        typeNumberKeyByKey("437", calc)
        calc.equals()
        assertEquals("19", calc.displayNumber.toString())

        // Math: 5^2 = 5 × 5 = 25
        // Operations: 5 × =
        typeNumberKeyByKey("5", calc)
        calc.multiply()
        calc.equals()
        assertEquals("25", calc.displayNumber.toString())

        // Math: 5^3 = 5 × 5 × 5 = 125
        // Operations: 5 × = =
        typeNumberKeyByKey("5", calc)
        calc.multiply()
        calc.equals()
        calc.equals()
        assertEquals("125", calc.displayNumber.toString())

        // Math: 5 ÷ 1 = 1
        // Operations: 5 ÷ =
        typeNumberKeyByKey("5", calc)
        calc.divide()
        calc.equals()
        assertEquals("1", calc.displayNumber.toString())

        // Math: 1 ÷ 5 = 0.2
        // Operations: 5 ÷ =
        typeNumberKeyByKey("1", calc)
        calc.divide()
        typeNumberKeyByKey("5", calc)
        calc.equals()
        assertEquals("0.2", calc.displayNumber.toString())

        // Math: √4 = 2
        // Operations: 4 √
        typeNumberKeyByKey("4", calc)
        calc.squareRoot()
        assertEquals("2", calc.displayNumber.toString())

        // Math: 75 × 6 ÷ 100 = 4.5
        // Operations: 75 × 6 %
        typeNumberKeyByKey("75", calc)
        calc.multiply()
        typeNumberKeyByKey("6", calc)
        calc.percent()
        assertEquals("4.5", calc.displayNumber.toString())
        calc.equals()
        assertEquals("337.5", calc.displayNumber.toString())

        // Math: 125 ÷ (625 × 125 ÷ 100) = 0.16
        // Operations: 125 ÷ 625 %
        typeNumberKeyByKey("125", calc)
        calc.divide()
        typeNumberKeyByKey("625", calc)
        calc.percent()
        assertEquals("781.25", calc.displayNumber.toString())
        calc.equals()
        assertEquals("0.16", calc.displayNumber.toString())

        // Math: 7.55 + (7.55 × 15%) = 8.6825
        // Operations: 7.55 + 15 %
        typeNumberKeyByKey("7.55", calc)
        calc.add()
        typeNumberKeyByKey("15", calc)
        calc.percent()
        assertEquals("1.1325", calc.displayNumber.toString())
        calc.equals()
        assertEquals("8.6825", calc.displayNumber.toString())

        // Math: 7.55 - (7.55 × 10%) = 6.795
        // Operations: 7.55 - 10 %
        typeNumberKeyByKey("7.55", calc)
        calc.subtract()
        typeNumberKeyByKey("10", calc)
        calc.percent()
        assertEquals("0.755", calc.displayNumber.toString())
        calc.equals()
        assertEquals("6.795", calc.displayNumber.toString())

        // Math: (10 × 20) - (5 × 10) = 150
        // Operations: CE 10 × 20 M+
        // Operations: 5 × 10 M-
        // Operations: MRC
        // Operations: MRC
        calc.ce()
        typeNumberKeyByKey("10", calc)
        calc.multiply()
        typeNumberKeyByKey("20", calc)
        calc.memoryAdd()
        typeNumberKeyByKey("5", calc)
        calc.multiply()
        typeNumberKeyByKey("10", calc)
        calc.memorySubtract()
        calc.memoryResultAndClean()
        assertEquals("150", calc.displayNumber.toString())
        calc.memoryResultAndClean()
        assertEquals("150", calc.displayNumber.toString())

        // Math: 9638527 × 3 = 28915581
        // Operations: 9638527 × 3 =
        typeNumberKeyByKey("9638527", calc)
        calc.multiply()
        typeNumberKeyByKey("3", calc)
        calc.equals()
        assertEquals("28915581", calc.displayNumber.toString())
    }

    fun typeNumberKeyByKey(strNumber: String, calc: Calculator) {
        for (number in strNumber) {
            if (number == '.') {
                calc.typeDot()
            } else {
                calc.typeNumber(number)
            }
        }
    }

    @Test
    fun checkMustIgnoreDotAtLeftDuringCalculations() {
        var calc = Calculator()
        typeNumberKeyByKey("9999999.5", calc)
        assertEquals("9999999.5", calc.displayNumber.toString())
        calc.multiply()
        typeNumberKeyByKey("2", calc)
        calc.equals()
        assertEquals("19999999", calc.displayNumber.toString())
    }

    @Test
    fun checkMustIgnoreZeroWithScaleDuringCalculations() {
        var calc = Calculator()
        typeNumberKeyByKey("0.0000002", calc)
        assertEquals("0.0000002", calc.displayNumber.toString())
        calc.divide()
        typeNumberKeyByKey("2", calc)
        calc.equals()
        assertEquals("0.0000001", calc.displayNumber.toString())
        calc.equals()
        assertEquals("0", calc.displayNumber.toString())
    }

    @Test
    fun checkRetoringState() {
        //Given the current Status:
        val numberOnDisplay: String = "22.042"
        val currentCalcTotal: String = "0"
        val currentOperation: Operations = Operations.MULTIPLICATION
        val currentNumberInMemory: String = "10.01"
        val isMemoryInUse: Boolean = true
        val mustCleanDisplayOnNextInteraction: Boolean = true

        // When the Calculator is restored
        var calc = Calculator()

        // And the old values are passed again to restore the state
        calc.restoreStatus(numberOnDisplay,
                currentCalcTotal,
                currentOperation,
                currentNumberInMemory,
                isMemoryInUse,
                mustCleanDisplayOnNextInteraction)

        // The calculator is in the same state as before
        assertEquals(numberOnDisplay, calc.displayNumber.toString())
        assertEquals(currentOperation, calc.currentOperation)
        assertEquals(currentNumberInMemory, calc.getCurrentNumberInMemory().toString())
        assertEquals(isMemoryInUse, calc.isMemoryInUse())
        assertEquals(mustCleanDisplayOnNextInteraction, calc.mustcleanDisplayOnNextInteraction())
    }
}
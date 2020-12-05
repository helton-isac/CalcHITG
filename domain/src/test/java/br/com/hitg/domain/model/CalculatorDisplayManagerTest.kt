package br.com.hitg.domain.model

import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class CalculatorDisplayManagerTest {

    @Test
    fun checkToString() {
        val display = CalculatorDisplayManager()
        assertEquals("0", display.toString())
    }

    @Test
    fun checkAppendNumber() {
        val display = CalculatorDisplayManager()
        display.appendNumber('1')
        display.appendNumber('2')
        display.appendNumber('3')
        assertEquals("123", display.toString())
    }

    @Test
    fun checkDecimalSeparator() {
        val display = CalculatorDisplayManager()
        display.appendDecimalSeparator()
        assertEquals("0.", display.toString())
        display.appendNumber('1')
        assertEquals("0.1", display.toString())
        display.appendNumber('2')
        assertEquals("0.12", display.toString())
        display.appendDecimalSeparator()
        assertEquals("0.12", display.toString())
    }

    @Test
    fun checkToBigDecimalMustParseDisplayToBigDecimal() {
        val display = CalculatorDisplayManager()
        assertEquals(BigDecimal("0"), display.toBigDecimal())
        display.appendDecimalSeparator()
        assertEquals(BigDecimal("0."), display.toBigDecimal())
        display.appendNumber('1')
        assertEquals(BigDecimal("0.1"), display.toBigDecimal())
        display.appendNumber('2')
        assertEquals(BigDecimal("0.12"), display.toBigDecimal())
    }

    @Test
    fun checkMustSetValue() {
        val display = CalculatorDisplayManager()
        display.setValue("123456789.123456789")
        assertEquals("123456789.123456789", display.toString())
        display.setValue("987654321.987654321")
        assertEquals("987654321.987654321", display.toString())
        display.setValue("0")
        assertEquals("0", display.toString())
        display.setValue("123454321")
        assertEquals("123454321", display.toString())
    }

    @Test
    fun checkMustResetDisplayWhenReplaceCurrentDisplayParamIsTrue() {
        val display = CalculatorDisplayManager()
        display.appendDecimalSeparator()
        display.appendNumber('1')
        display.appendNumber('2')
        assertEquals("0.12", display.toString())
        display.appendNumber('1', true)
        display.appendNumber('1')
        display.appendNumber('1')
        assertEquals("111", display.toString())
        display.setValue("987654321")
        assertEquals("987654321", display.toString())
        display.appendNumber('9', true)
        display.appendNumber('8')
        display.appendNumber('7')
        assertEquals("987", display.toString())
    }

    @Test
    fun checkMustResetDisplayWithDecimalSeparatorWhenReplaceCurrentDisplayParamIsTrue() {
        val display = CalculatorDisplayManager("1.2")
        assertTrue(display.appendDecimalSeparator(true))
        assertEquals("0.", display.toString())
    }

    @Test
    fun checkMustThrowExceptionWhenDisplayIsNotANumber() {
        val display = CalculatorDisplayManager()
        display.setValue("ERROR")
        try {
            display.toBigDecimal()
        } catch (e: Exception) {
            assertTrue(e is NumberFormatException)
        }
        display.setValue("0")
        assertEquals(BigDecimal("0"), display.toBigDecimal())
    }

    @Test
    fun checkInvalidInputMustThrowError() {
        val display = CalculatorDisplayManager()
        try {
            display.appendNumber('E')
            fail("Must throws an IllegalArgumentException")
        } catch (e: Exception) {
            assertTrue(e is IllegalArgumentException)
        }
    }

    @Test
    fun checkInitialValueConstructor() {
        var display = CalculatorDisplayManager("123")
        assertEquals("123", display.toString())
        display = CalculatorDisplayManager("TEST")
        assertEquals("TEST", display.toString())
        display = CalculatorDisplayManager("")
        assertEquals("", display.toString())
        display = CalculatorDisplayManager("Any Value Here")
        assertEquals("Any Value Here", display.toString())
    }

    @Test
    fun checkMaxValueConstructor() {
        val display = CalculatorDisplayManager(8)
        display.setValue("1234567")
        assertEquals("1234567", display.toString())
        display.appendNumber('8')
        assertEquals("12345678", display.toString())
        display.appendNumber('9')
        assertEquals("12345678", display.toString())
        display.appendNumber('0')
        assertEquals("12345678", display.toString())
        display.appendNumber('0', true)
        assertEquals("0", display.toString())
    }

    @Test
    fun checkMustThrowExceptionWhenSetValueGreaterThanMaxValue() {
        val display = CalculatorDisplayManager(8)
        try {
            display.setValue("987654321")
        } catch (e: Exception) {
            assertTrue(e is ArithmeticException)
        }
        try {
            display.setValue("1234567890")
        } catch (e: Exception) {
            assertTrue(e is ArithmeticException)
        }
    }

    @Test
    fun checkMaxValueInfinity() {
        val display = CalculatorDisplayManager(-1)
        display.setValue("1")
        assertEquals("1", display.toString())
        display.appendNumber('8')
        assertEquals("18", display.toString())
        display.setValue("9876543210987654321098765432109876543210987654321098765432109876543210")
        assertEquals("9876543210987654321098765432109876543210987654321098765432109876543210",
                display.toString())
        display.appendNumber('9')
        assertEquals("98765432109876543210987654321098765432109876543210987654321098765432109",
                display.toString())
    }

    @Test
    fun checkMustThrowExceptionMaxValueZero() {
        try {
            CalculatorDisplayManager(0)
        } catch (e: Exception) {
            assertTrue(e is IllegalArgumentException)
        }
    }

    @Test
    fun checkRemoveLastMustReturnTrueWhenSuccess() {
        val display = CalculatorDisplayManager("12345678")
        assertTrue(display.removeLast())
        assertEquals("1234567", display.toString())
        assertTrue(display.removeLast())
        assertEquals("123456", display.toString())
        display.setValue("10")
        assertTrue(display.removeLast())
        assertEquals("1", display.toString())
        assertTrue(display.removeLast())
        assertEquals("0", display.toString())
        display.setValue("-0.001")
        assertTrue(display.removeLast())
        assertTrue(display.removeLast())
        assertTrue(display.removeLast())
        assertTrue(display.removeLast())
        assertEquals("-0", display.toString())
        assertTrue(display.removeLast())
        assertEquals("0", display.toString())
    }

    @Test
    fun checkRemoveLastMustReturnFalseWhenNothingIsRemoved() {
        val display = CalculatorDisplayManager("0")
        assertEquals("0", display.toString())
        assertFalse(display.removeLast())
        assertEquals("0", display.toString())
        display.setValue("-1.1")
        assertTrue(display.removeLast())
        assertEquals("-1.", display.toString())
        assertTrue(display.removeLast())
        assertEquals("-1", display.toString())
        assertTrue(display.removeLast())
        assertEquals("0", display.toString())
        assertFalse(display.removeLast())
        assertEquals("0", display.toString())
    }

    @Test
    fun checkAppendNumberMustReturnTrueWhenAppends() {
        val display = CalculatorDisplayManager()
        assertTrue(display.appendNumber('1'))
        assertTrue(display.appendNumber('2'))
        assertTrue(display.appendNumber('3'))
        assertEquals("123", display.toString())
    }

    @Test
    fun checkAppendNumberMustReturnFalseWhenDoNotAppend() {
        var display = CalculatorDisplayManager()
        assertFalse(display.appendNumber('0'))
        assertTrue(display.appendNumber('1'))
        assertTrue(display.appendDecimalSeparator())
        assertFalse(display.appendDecimalSeparator())
        assertTrue(display.appendNumber('1'))
        assertTrue(display.appendNumber('1'))
        assertFalse(display.appendDecimalSeparator())
        assertEquals("1.11", display.toString())
        display = CalculatorDisplayManager(2)
        assertTrue(display.appendNumber('1'))
        assertTrue(display.appendNumber('1'))
        assertFalse(display.appendNumber('2'))
        assertFalse(display.appendNumber('2'))
        assertEquals("11", display.toString())
    }

    @Test
    fun checkMaxLengthMustNotCountForDecimalSeparator() {
        val display = CalculatorDisplayManager(2)
        assertTrue(display.appendNumber('1'))
        assertTrue(display.appendDecimalSeparator())
        assertTrue(display.appendNumber('2'))
        assertFalse(display.appendNumber('3'))
        assertEquals("1.2", display.toString())
    }

    @Test
    fun checkRemoveLastMustEnableUseDecimalSeparatorWhenRemoveADecimalSeparator() {
        var display = CalculatorDisplayManager()
        assertTrue(display.appendDecimalSeparator())
        assertEquals("0.", display.toString())
        assertFalse(display.appendDecimalSeparator())
        assertTrue(display.appendNumber('1'))
        assertFalse(display.appendDecimalSeparator())
        assertEquals("0.1", display.toString())
        assertTrue(display.removeLast())
        assertFalse(display.appendDecimalSeparator())
        assertTrue(display.removeLast())
        assertTrue(display.appendDecimalSeparator())
        assertEquals("0.", display.toString())

        display = CalculatorDisplayManager("123.123")
        assertFalse(display.appendDecimalSeparator())
        assertTrue(display.removeLast())
        assertFalse(display.appendDecimalSeparator())
        assertTrue(display.removeLast())
        assertFalse(display.appendDecimalSeparator())
        assertTrue(display.removeLast())
        assertTrue(display.removeLast())
        assertTrue(display.removeLast())
        assertTrue(display.appendDecimalSeparator())
        assertTrue(display.appendNumber('9'))
        assertFalse(display.appendDecimalSeparator())
        assertEquals("12.9", display.toString())
    }

    @Test
    fun checkValidNumber() {
        val display = CalculatorDisplayManager("1.1.2.3")
        try {
            display.toBigDecimal()
        } catch (e: Exception) {
            assertTrue(e is NumberFormatException)
        }
        assertEquals("1.1.2.3", display.toString())
        assertFalse(display.appendDecimalSeparator())
        assertEquals("1.1.2.3", display.toString())
        assertTrue(display.appendNumber('2'))
        assertEquals("2", display.toString())
        assertTrue(display.appendDecimalSeparator())
        assertTrue(display.appendNumber('3'))
        assertEquals("2.3", display.toString())
        display.setValue("ERROR")
        try {
            display.toBigDecimal()
        } catch (e: Exception) {
            assertTrue(e is NumberFormatException)
        }
        assertEquals("ERROR", display.toString())
        assertTrue(display.removeLast())
        assertEquals("0", display.toString())
        assertFalse(display.removeLast())
        assertEquals("0", display.toString())
        display.setValue("-")
        try {
            display.toBigDecimal()
        } catch (e: Exception) {
            assertTrue(e is NumberFormatException)
        }
        assertEquals("-", display.toString())
        assertTrue(display.removeLast())
        assertEquals("0", display.toString())
    }

    @Test
    fun checkMustIgnoreZeroWithScaleOnConstructor() {
        val display = CalculatorDisplayManager("0.00000")
        assertEquals("0", display.toString())
    }
}
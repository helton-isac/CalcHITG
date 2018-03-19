package br.com.hitg.simplecalculator.calculator

import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class CalculatorDisplayTest {

    @Test
    fun checkToString() {
        var display: CalculatorDisplay = CalculatorDisplay()
        assertEquals("0", display.toString())
    }

    @Test
    fun checkAppendNumber() {
        var display: CalculatorDisplay = CalculatorDisplay()
        display.appendNumber('1')
        display.appendNumber('2')
        display.appendNumber('3')
        assertEquals("123", display.toString())
    }

    @Test
    fun checkDecimalSeparator() {
        var display: CalculatorDisplay = CalculatorDisplay()
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
        var display: CalculatorDisplay = CalculatorDisplay()
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
        var display: CalculatorDisplay = CalculatorDisplay()
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
        var display: CalculatorDisplay = CalculatorDisplay()
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
    fun checkMustReturnNullWhenDisplayIsNotANumber() {
        var display: CalculatorDisplay = CalculatorDisplay()
        display.setValue("ERROR")
        assertNull(display.toBigDecimal())
        display.setValue("0")
        assertNotNull(display.toBigDecimal())
    }

    @Test
    fun checkInvalidInputMustThrowError() {
        var display: CalculatorDisplay = CalculatorDisplay()
        try {
            display.appendNumber('E')
            fail("Must throws an IllegalArgumentException")
        } catch (e: Exception) {
            assertTrue(e is IllegalArgumentException)
        }
    }

    @Test
    fun checkInitialValueConstructor() {
        var display: CalculatorDisplay = CalculatorDisplay("123")
        assertEquals("123", display.toString())
        display = CalculatorDisplay("TEST")
        assertEquals("TEST", display.toString())
        display = CalculatorDisplay("")
        assertEquals("", display.toString())
        display = CalculatorDisplay("Any Value Here")
        assertEquals("Any Value Here", display.toString())
    }

    @Test
    fun checkMaxValueConstructor() {
        var display: CalculatorDisplay = CalculatorDisplay(8)
        display.setValue("1234567")
        assertEquals("1234567", display.toString())
        display.appendNumber('8')
        assertEquals("12345678", display.toString())
        display.appendNumber('9')
        assertEquals("12345678", display.toString())
        display.appendNumber('0')
        assertEquals("12345678", display.toString())
        display.setValue("987654321")
        assertEquals("98765432", display.toString())
        display.setValue("1234567890")
        assertEquals("12345678", display.toString())
        display.appendNumber('0', true)
        assertEquals("0", display.toString())
    }

    @Test
    fun checkMaxValueInfinity() {
        var display: CalculatorDisplay = CalculatorDisplay(-1)
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
    fun checkMaxValueZero() {
        var display: CalculatorDisplay = CalculatorDisplay(0)
        display.setValue("1")
        assertEquals("", display.toString())
        display.appendNumber('8')
        assertEquals("", display.toString())
    }

    @Test
    fun checkRemoveLast() {
        var display: CalculatorDisplay = CalculatorDisplay("12345678")
        display.removeLast()
        assertEquals("1234567", display.toString())
        display.removeLast()
        assertEquals("123456", display.toString())
        display.setValue("10")
        display.removeLast()
        assertEquals("1", display.toString())
        display.removeLast()
        assertEquals("0", display.toString())
        display.removeLast()
        assertEquals("0", display.toString())
        display.setValue("123.123")
        display.removeLast()
        assertEquals("123.12", display.toString())
        display.removeLast()
        assertEquals("123.1", display.toString())
        display.removeLast()
        assertEquals("123.", display.toString())
        display.removeLast()
        assertEquals("123", display.toString())
        display.removeLast()
        assertEquals("12", display.toString())
        display.removeLast()
        assertEquals("1", display.toString())
        display.removeLast()
        assertEquals("0", display.toString())
    }
}
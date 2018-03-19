package br.com.hitg.simplecalculator.calculator

import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

/**
 * Created by Helton on 14/03/2018.
 *
 * Class Used to test the MathEngine
 */
class MathEngineTest {

    @Test
    fun addSimple() {
        assertAdd("Must return 10", "10", "4", "6")
        assertAdd("Must return -10", "-10", "-4", "-6")
        assertAdd("Must return -2", "-2", "4", "-6")
        assertAdd("Must return 2", "2", "-4", "6")
        assertAdd("Must return 99999999", "99999999", "22222222", "77777777")
        assertAdd("Must return -99999999", "-99999999", "-22222222", "-77777777")
        assertAdd("Must return 55555555", "55555555", "-22222222", "77777777")
        assertAdd("Must return -55555555", "-55555555", "22222222", "-77777777")
    }

    @Test
    fun addDecimal() {
        assertAdd("Must return 5.00000002", "5.00000002", "2.00000001", "3.00000001")
        assertAdd("Must return 99999999.99999999",
                "99999999.99999999",
                "22222222.22222222",
                "77777777.77777777")
        assertAdd("Must return -99999999.99999999",
                "-99999999.99999999",
                "-22222222.22222222",
                "-77777777.77777777")
    }


    @Test
    fun subtractSimple() {
        assertSubtract("Must return 10", "10", "40", "30")
        assertSubtract("Must return -70", "-70", "-40", "30")
        assertSubtract("Must return 10", "10", "4", "-6")
        assertSubtract("Must return -10", "-10", "-4", "6")
        assertSubtract("Must return -55555555", "-55555555", "22222222", "77777777")
        assertSubtract("Must return 55555555", "55555555", "-22222222", "-77777777")
        assertSubtract("Must return -99999999", "-99999999", "-22222222", "77777777")
        assertSubtract("Must return 99999999", "99999999", "22222222", "-77777777")
    }

    @Test
    fun subtractDecimal() {
        assertSubtract("Must return 5.99999999", "5.99999999", "10", "4.00000001")
        assertSubtract("Must return 2.77777777", "2.77777777", "5.99999999", "3.22222222")
        assertSubtract("Must return -55555555.55555555",
                "-55555555.55555555",
                "22222222.22222222",
                "77777777.77777777")
        assertSubtract("Must return 55555555.55555555",
                "55555555.55555555",
                "-22222222.22222222",
                "-77777777.77777777")
        assertSubtract("Must return -99999999.99999999",
                "-99999999.99999999",
                "-22222222.22222222",
                "77777777.77777777")
        assertSubtract("Must return 99999999.99999999",
                "99999999.99999999",
                "22222222.22222222",
                "-77777777.77777777")
    }


    @Test
    fun multiplySimple() {
        assertMultiply("Must return 12.00000000", "12.00000000", "4", "3")
        assertMultiply("Must return -12.00000000", "-12.00000000", "-4", "3")
        assertMultiply("Must return -12.00000000", "-12.00000000", "4", "-3")
        assertMultiply("Must return 1.000000002", "12.00000000", "-4", "-3")
        assertMultiply("Must return 17280494.00000000", "17280494.00000000", "2222", "7777")
        assertMultiply("Must return -17280494.00000000", "-17280494.00000000", "-2222", "7777")
        assertMultiply("Must return -17280494.00000000", "-17280494.00000000", "2222", "-7777")
        assertMultiply("Must return 17280494.00000000", "17280494.00000000", "-2222", "-7777")
        assertMultiply("Must return 0.00000000", "0.00000000", "0", "0")
        assertMultiply("Must return 0.00000000", "0.00000000", "0", "42")
        assertMultiply("Must return 0.00000000", "0.00000000", "42", "0")
        assertMultiply("Must return 42.00000000", "42.00000000", "42", "1")
        assertMultiply("Must return 42.00000000", "42.00000000", "1", "42")
        assertMultiply("Must return -42.00000000", "-42.00000000", "42", "-1")
        assertMultiply("Must return -42.00000000", "-42.00000000", "-1", "42")
    }

    @Test
    fun multiplyDecimal() {
        assertMultiply("Must return 11.99999992", "11.99999992", "1.99999999", "5.99999999")
        assertMultiply("Must return -11.99999992", "-11.99999992", "1.99999999", "-5.99999999")
        assertMultiply("Must return -11.99999992", "-11.99999992", "-1.99999999", "5.99999999")
        assertMultiply("Must return 11.99999992", "11.99999992", "-1.99999999", "-5.99999999")
        assertMultiply("Must return 56200.23208800", "56200.23208800", "123.123", "456.456")
        assertMultiply("Must return -56200.23208800", "-56200.23208800", "-123.123", "456.456")
        assertMultiply("Must return -56200.23208800", "-56200.23208800", "123.123", "-456.456")
        assertMultiply("Must return 56200.23208800", "56200.23208800", "-123.123", "-456.456")
        assertMultiply("Must return 0.00000000", "0.00000000", "123.123", "0")
        assertMultiply("Must return 0.00000000", "0.00000000", "-123.123", "0")
        assertMultiply("Must return 0.00000000", "0.00000000", "0", "123.123")
        assertMultiply("Must return 0.00000000", "0.00000000", "0", "-123.123")
        assertMultiply("Must return 124.33100000", "124.33100000", "1.01", "123.1")
    }

    @Test
    fun divideSimple() {
        assertDivide("Must return 2.00000000", "2.00000000", "10", "5")
        assertDivide("Must return -2.00000000", "-2.00000000", "10", "-5")
        assertDivide("Must return -2.00000000", "-2.00000000", "-10", "5")
        assertDivide("Must return 2.00000000", "2.00000000", "-10", "-5")
        assertDivide("Must return 0.00000000", "0.00000000", "0", "42")
        assertDivide("Must return 42.00000000", "42.00000000", "42", "1")
        assertDivide("Must return 0.02380952", "0.02380952", "1", "42")
        assertDivide("Must return -42.00000000", "-42.00000000", "42", "-1")
        assertDivide("Must return -0.02380952", "-0.02380952", "-1", "42")
    }

    @Test
    fun divideDecimal() {
        assertDivide("Must return 3.70731707", "3.70731707", "456.456", "123.123")
        assertDivide("Must return -3.70731707", "-3.70731707", "456.456", "-123.123")
        assertDivide("Must return -3.70731707", "-3.70731707", "-456.456", "123.123")
        assertDivide("Must return 3.70731707", "3.70731707", "-456.456", "-123.123")

    }

    @Test
    fun divideMustThrowsArithmeticException() {
        assertDivideThrowErrors("Must throws ArithmeticException", "1", "0")
        assertDivideThrowErrors("Must throws ArithmeticException", "0", "0")
        assertDivideThrowErrors("Must throws ArithmeticException", "42", "0")
        assertDivideThrowErrors("Must throws ArithmeticException", "456.456", "0")
        assertDivideThrowErrors("Must throws ArithmeticException", "-456.456", "0")
        assertDivideThrowErrors("Must throws ArithmeticException", "456.456", "-0")
    }

    private fun assertAdd(message: String, expected: String, v1: String, v2: String) {
        assertEquals(message,
                BigDecimal(expected), MathEngine.add(BigDecimal(v1), BigDecimal(v2)))
    }

    private fun assertSubtract(message: String, expected: String, v1: String, v2: String) {
        assertEquals(message,
                BigDecimal(expected), MathEngine.subtract(BigDecimal(v1), BigDecimal(v2)))
    }

    private fun assertMultiply(message: String, expected: String, v1: String, v2: String) {
        assertEquals(message,
                BigDecimal(expected), MathEngine.multiply(BigDecimal(v1), BigDecimal(v2)))
    }

    private fun assertDivide(message: String, expected: String, v1: String, v2: String) {
        assertEquals(message,
                BigDecimal(expected), MathEngine.divide(BigDecimal(v1), BigDecimal(v2)))
    }

    private fun assertDivideThrowErrors(message: String, v1: String, v2: String) {
        try {
            MathEngine.divide(BigDecimal(v1), BigDecimal(v2))
            fail(message)
        } catch (e: Exception) {
            assertTrue(message, e is ArithmeticException)
        }
    }
}

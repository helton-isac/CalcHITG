package br.com.hitg.calculator.calculator

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
        assertAdd("Must return 10.0000000000", "10.0000000000", "4", "6")
        assertAdd("Must return -10.0000000000", "-10.0000000000", "-4", "-6")
        assertAdd("Must return -2.0000000000", "-2.0000000000", "4", "-6")
        assertAdd("Must return 2.0000000000", "2.0000000000", "-4", "6")
        assertAdd("Must return 99999999.0000000000", "99999999.0000000000", "22222222", "77777777")
        assertAdd("Must return -99999999.0000000000", "-99999999.0000000000", "-22222222", "-77777777")
        assertAdd("Must return 55555555.0000000000", "55555555.0000000000", "-22222222", "77777777")
        assertAdd("Must return -55555555.0000000000", "-55555555.0000000000", "22222222", "-77777777")
    }

    @Test
    fun addDecimal() {
        assertAdd("Must return 5.0000000002", "5.0000000002", "2.0000000001", "3.0000000001")
        assertAdd("Must return 9999999999.9999999999",
                "9999999999.9999999999",
                "2222222222.2222222222",
                "7777777777.7777777777")
        assertAdd("Must return -9999999999.9999999999",
                "-9999999999.9999999999",
                "-2222222222.2222222222",
                "-7777777777.7777777777")
    }


    @Test
    fun subtractSimple() {
        assertSubtract("Must return 10.0000000000", "10.0000000000", "40", "30")
        assertSubtract("Must return -70.0000000000", "-70.0000000000", "-40", "30")
        assertSubtract("Must return 10.0000000000", "10.0000000000", "4", "-6")
        assertSubtract("Must return -10.0000000000", "-10.0000000000", "-4", "6")
        assertSubtract("Must return -55555555.0000000000", "-55555555.0000000000", "22222222", "77777777")
        assertSubtract("Must return 55555555.0000000000", "55555555.0000000000", "-22222222", "-77777777")
        assertSubtract("Must return -99999999.0000000000", "-99999999.0000000000", "-22222222", "77777777")
        assertSubtract("Must return 99999999.0000000000", "99999999.0000000000", "22222222", "-77777777")
    }

    @Test
    fun subtractDecimal() {
        assertSubtract("Must return 5.9999999999", "5.9999999999", "10", "4.0000000001")
        assertSubtract("Must return 2.7777777777", "2.7777777777", "5.9999999999", "3.2222222222")
        assertSubtract("Must return -5555555555.5555555555",
                "-5555555555.5555555555",
                "2222222222.2222222222",
                "7777777777.7777777777")
        assertSubtract("Must return 5555555555.5555555555",
                "5555555555.5555555555",
                "-2222222222.2222222222",
                "-7777777777.7777777777")
        assertSubtract("Must return -9999999999.9999999999",
                "-9999999999.9999999999",
                "-2222222222.2222222222",
                "7777777777.7777777777")
        assertSubtract("Must return 9999999999.9999999999",
                "9999999999.9999999999",
                "2222222222.2222222222",
                "-7777777777.7777777777")
    }


    @Test
    fun multiplySimple() {
        assertMultiply("Must return 12.0000000000", "12.0000000000", "4", "3")
        assertMultiply("Must return -12.0000000000", "-12.0000000000", "-4", "3")
        assertMultiply("Must return -12.0000000000", "-12.0000000000", "4", "-3")
        assertMultiply("Must return 1.00000000002", "12.0000000000", "-4", "-3")
        assertMultiply("Must return 17280494.0000000000", "17280494.0000000000", "2222", "7777")
        assertMultiply("Must return -17280494.0000000000", "-17280494.0000000000", "-2222", "7777")
        assertMultiply("Must return -17280494.0000000000", "-17280494.0000000000", "2222", "-7777")
        assertMultiply("Must return 17280494.0000000000", "17280494.0000000000", "-2222", "-7777")
        assertMultiply("Must return 0.0000000000", "0.0000000000", "0", "0")
        assertMultiply("Must return 0.0000000000", "0.0000000000", "0", "42")
        assertMultiply("Must return 0.0000000000", "0.0000000000", "42", "0")
        assertMultiply("Must return 42.0000000000", "42.0000000000", "42", "1")
        assertMultiply("Must return 42.0000000000", "42.0000000000", "1", "42")
        assertMultiply("Must return -42.0000000000", "-42.0000000000", "42", "-1")
        assertMultiply("Must return -42.0000000000", "-42.0000000000", "-1", "42")
    }

    @Test
    fun multiplyDecimal() {
        assertMultiply("Must return 11.9999999992", "11.9999999992", "1.9999999999", "5.9999999999")
        assertMultiply("Must return -11.9999999993", "-11.9999999993", "1.9999999999", "-5.9999999999")
        assertMultiply("Must return -11.9999999993", "-11.9999999993", "-1.9999999999", "5.9999999999")
        assertMultiply("Must return 11.9999999992", "11.9999999992", "-1.9999999999", "-5.9999999999")
        assertMultiply("Must return 56200.2320880000", "56200.2320880000", "123.123", "456.456")
        assertMultiply("Must return -56200.2320880000", "-56200.2320880000", "-123.123", "456.456")
        assertMultiply("Must return -56200.2320880000", "-56200.2320880000", "123.123", "-456.456")
        assertMultiply("Must return 56200.2320880000", "56200.2320880000", "-123.123", "-456.456")
        assertMultiply("Must return 0.0000000000", "0.0000000000", "123.123", "0")
        assertMultiply("Must return 0.0000000000", "0.0000000000", "-123.123", "0")
        assertMultiply("Must return 0.0000000000", "0.0000000000", "0", "123.123")
        assertMultiply("Must return 0.0000000000", "0.0000000000", "0", "-123.123")
        assertMultiply("Must return 124.3310000000", "124.3310000000", "1.01", "123.1")
    }

    @Test
    fun divideSimple() {
        assertDivide("Must return 2.0000000000", "2.0000000000", "10", "5")
        assertDivide("Must return -2.0000000000", "-2.0000000000", "10", "-5")
        assertDivide("Must return -2.0000000000", "-2.0000000000", "-10", "5")
        assertDivide("Must return 2.0000000000", "2.0000000000", "-10", "-5")
        assertDivide("Must return 0.0000000000", "0.0000000000", "0", "42")
        assertDivide("Must return 42.0000000000", "42.0000000000", "42", "1")
        assertDivide("Must return 0.0238095238", "0.0238095238", "1", "42")
        assertDivide("Must return -42.0000000000", "-42.0000000000", "42", "-1")
        assertDivide("Must return -0.0238095239", "-0.0238095239", "-1", "42")
    }

    @Test
    fun divideDecimal() {
        assertDivide("Must return 3.7073170731", "3.7073170731", "456.456", "123.123")
        assertDivide("Must return -3.7073170732", "-3.7073170732", "456.456", "-123.123")
        assertDivide("Must return -3.7073170732", "-3.7073170732", "-456.456", "123.123")
        assertDivide("Must return 3.7073170731", "3.7073170731", "-456.456", "-123.123")

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

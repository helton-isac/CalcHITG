package br.com.hitg.simplecalculator.calculator

import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigDecimal

/**
 * Created by Helton on 14/03/2018.
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
        assertAdd("Must return 5.00000002",
                "5.00000002", "2.00000001", "3.00000001")
        assertAdd("Must return 99999999.99999999",
                "99999999.99999999", "22222222.22222222", "77777777.77777777")
        assertAdd("Must return -99999999.99999999",
                "-99999999.99999999", "-22222222.22222222", "-77777777.77777777")
    }

    fun assertAdd(message: String, expected: String, v1: String, v2: String) {
        assertTrue(message,
                BigDecimal(expected).equals(MathEngine.add(BigDecimal(v1), BigDecimal(v2))))
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
        assertSubtract("Must return 5.99999999",
                "5.99999999", "10", "4.00000001")
        assertSubtract("Must return 2.77777777",
                "2.77777777", "5.99999999", "3.22222222")
        assertSubtract("Must return -55555555.55555555",
                "-55555555.55555555", "22222222.22222222", "77777777.77777777")
        assertSubtract("Must return 55555555.55555555",
                "55555555.55555555", "-22222222.22222222", "-77777777.77777777")
        assertSubtract("Must return -99999999.99999999",
                "-99999999.99999999", "-22222222.22222222", "77777777.77777777")
        assertSubtract("Must return 99999999.99999999",
                "99999999.99999999", "22222222.22222222", "-77777777.77777777")
    }

    fun assertSubtract(message: String, expected: String, v1: String, v2: String) {
        assertTrue(message,
                BigDecimal(expected).equals(MathEngine.subtract(BigDecimal(v1), BigDecimal(v2))))
    }

}

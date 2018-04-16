package br.com.hitg.calculator.calculator

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal


class CalculatorUserMemoryTest {

    private lateinit var userMemory: CalculatorUserMemory

    @Before
    fun setUp() {
        userMemory = CalculatorUserMemory()
    }

    @After
    fun tearDown() {
        userMemory.mrc()
    }

    @Test
    fun isMemoryInUse() {
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
        userMemory.mPlus(BigDecimal("2"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mPlus(BigDecimal("2"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mrc()
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
        userMemory.mSubtract(BigDecimal("2"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mSubtract(BigDecimal("2"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mrc()
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
        userMemory.mPlus(BigDecimal("42"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mSubtract(BigDecimal("2"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mrc()
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
        userMemory.mSubtract(BigDecimal("42"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mPlus(BigDecimal("2"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mrc()
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)

    }

    @Test
    fun mrc() {
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
        userMemory.mPlus(BigDecimal("42"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mSubtract(BigDecimal("2"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        assertEquals("The user memory must be 40", BigDecimal("40"), userMemory.mrc())
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
    }

    @Test
    fun currentMemoryValue() {
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
        userMemory.mPlus(BigDecimal("42"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mSubtract(BigDecimal("2"))
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        assertEquals("The user memory must be 40",
                BigDecimal("40"),
                userMemory.currentMemoryValue())
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
        userMemory.mSubtract(BigDecimal("2"))
        assertEquals("The user memory must be 38",
                BigDecimal("38"),
                userMemory.currentMemoryValue())
    }

    @Test
    fun mPlus() {
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
        userMemory.mPlus(BigDecimal("42"))
        assertEquals("The user memory must be 42",
                BigDecimal("42"),
                userMemory.currentMemoryValue())
        userMemory.mPlus(BigDecimal("0.00001"))
        assertEquals("The user memory must be 42.00001",
                BigDecimal("42.00001"),
                userMemory.currentMemoryValue())
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
    }

    @Test
    fun mSubtract() {
        assertFalse("The isMemoryInUse must be false", userMemory.isMemoryInUse)
        userMemory.mSubtract(BigDecimal("42"))
        assertEquals("The user memory must be -42",
                BigDecimal("-42"),
                userMemory.currentMemoryValue())
        userMemory.mSubtract(BigDecimal("0.00001"))
        assertEquals("The user memory must be -42.00001",
                BigDecimal("-42.00001"),
                userMemory.currentMemoryValue())
        assertTrue("The isMemoryInUse must be true", userMemory.isMemoryInUse)
    }

}
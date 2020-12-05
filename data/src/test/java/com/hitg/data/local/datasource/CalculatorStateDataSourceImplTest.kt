package com.hitg.data.local.datasource

import br.com.hitg.domain.model.ICalculatorState
import br.com.hitg.domain.model.Operations
import com.hitg.data.local.database.CalculatorStateDAO
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.Mockito

internal class CalculatorStateDataSourceImplTest {

    private lateinit var sut: CalculatorStateDataSourceImpl

    @Nested
    @DisplayName("Given A CalculatorStateDAO")
    inner class GivenACalculatorStateDAO {

        var calculatorStateDAO = mock<CalculatorStateDAO>()

        @BeforeEach
        fun before() {
            sut = CalculatorStateDataSourceImpl(calculatorStateDAO)
        }

        @Nested
        @DisplayName("When The Implementation Asks For A Calculator State")
        inner class WhenTheImplementationAsksForACalculatorState {

            var calculatorState = mock<ICalculatorState>()

            @BeforeEach
            fun before() {
                Mockito.`when`(calculatorStateDAO.getCalculatorState())
                        .thenReturn(calculatorState)
            }

            @org.junit.jupiter.api.Test
            @DisplayName("It should return the real Calculator State")
            fun shouldReturnTheRealCalculatorState() {
                val calculatorStateResult = sut.getCalculatorState()
                Assert.assertEquals(calculatorState, calculatorStateResult)
            }

            @org.junit.jupiter.api.Test
            @DisplayName("The DAO should be called to get the state")
            fun daoShouldBeCalledToPersistTheState() {
                sut.getCalculatorState()

                Mockito.verify(
                        calculatorStateDAO,
                        Mockito.times(1)
                ).getCalculatorState()
            }
        }

        @Nested
        @DisplayName("And A New CalculatorState To Save")
        inner class AndANewCalculatorStateToSave {

            var calculatorState = mock<ICalculatorState>()

            @BeforeEach
            fun before() {
                whenever(calculatorState.displayValue).thenReturn("22.042")
                whenever(calculatorState.calcTotal).thenReturn("0")
                whenever(calculatorState.currentOperation).thenReturn(Operations.MULTIPLICATION.name)
                whenever(calculatorState.numberInMemory).thenReturn("10.01")
                whenever(calculatorState.isMemoryInUse).thenReturn(true)
                whenever(calculatorState.mustCleanDisplayOnNextInteraction).thenReturn(true)
                whenever(calculatorState.lastOperation).thenReturn(Operations.NONE.name)
                whenever(calculatorState.lastInputValue).thenReturn("0")
            }

            @org.junit.jupiter.api.Test
            @DisplayName("The DAO should be called to persist the state")
            fun daoShouldBeCalledToPersistTheState() {
                sut.persistCalculatorState(calculatorState)

                Mockito.verify(calculatorStateDAO, Mockito.times(1))
                        .persistCalculatorState(calculatorState)
            }
        }
    }
}
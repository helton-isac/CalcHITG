package br.com.hitg.domain.usecases

import br.com.hitg.domain.mathEngine.MathEngine
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import java.math.BigDecimal

class DivisionUseCaseTest {

    private lateinit var sut: DivisionUseCase

    @Nested
    @DisplayName("Given any MathEngine Implementation")
    inner class GivenAnyMathEngine {

        var mathEngine = mock<MathEngine>()

        @BeforeEach
        fun before() {
            sut = DivisionUseCase(mathEngine)
        }

        @Nested
        @DisplayName("When two values are passed to the use Case")
        inner class WhenTwoValuesArePassed {

            private val v1: BigDecimal = BigDecimal("1")
            private val v2: BigDecimal = BigDecimal("2")

            @Test
            @DisplayName("The divide method must be called with the same arguments")
            fun checkDivideWasCalled() {
                sut.execute(v1, v2)
                verify(mathEngine, times(1)).divide(v1, v2)
            }
        }
    }
}
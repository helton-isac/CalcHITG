package br.com.hitg.domain.mathEngine

import java.math.BigDecimal
import java.math.RoundingMode

class SimpleMathEngine : MathEngine {

    private val defaultScale: Int = 10

    private val defaultRoundingMode: RoundingMode = RoundingMode.FLOOR

    override fun add(v1: BigDecimal, v2: BigDecimal): BigDecimal =
            v1.add(v2).setScale(defaultScale, defaultRoundingMode)

    override fun subtract(v1: BigDecimal, v2: BigDecimal): BigDecimal =
            v1.subtract(v2).setScale(defaultScale, defaultRoundingMode)

    override fun multiply(v1: BigDecimal, v2: BigDecimal): BigDecimal =
            v1.multiply(v2).setScale(defaultScale, defaultRoundingMode)

    override fun divide(v1: BigDecimal, v2: BigDecimal): BigDecimal =
            v1.divide(v2, defaultScale, defaultRoundingMode)
}
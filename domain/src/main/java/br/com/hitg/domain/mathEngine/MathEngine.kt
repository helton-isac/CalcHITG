package br.com.hitg.domain.mathEngine

import java.math.BigDecimal

interface MathEngine {

    fun add(v1: BigDecimal, v2: BigDecimal): BigDecimal
    fun subtract(v1: BigDecimal, v2: BigDecimal): BigDecimal
    fun multiply(v1: BigDecimal, v2: BigDecimal): BigDecimal
    fun divide(v1: BigDecimal, v2: BigDecimal): BigDecimal
}
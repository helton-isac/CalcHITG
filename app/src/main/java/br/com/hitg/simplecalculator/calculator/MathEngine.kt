package br.com.hitg.simplecalculator.calculator

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by Helton on 13/03/2018.
 *
 * Class responsible for performing calculations
 */
class MathEngine {
    companion object {

        /**
         * Default digit scale.
         */
        private val defaultScale: Int = 8

        /**
         * Default Rounding Mode.
         */
        private val defaultRoundingMode: RoundingMode = RoundingMode.HALF_UP

        /**
         * Returns a BigDecimal whose value is (v1 + v2), and whose scale is
         * max(v1.scale(), v2.scale()).
         *
         * @param v1 value 1.
         * @param v2 value 2.
         * @return a BigDecimal whose value is (v1 + v2).
         */
        fun add(v1: BigDecimal, v2: BigDecimal): BigDecimal =
                v1.add(v2)

        /**
         * Returns a BigDecimal whose value is (v1 - v2), and whose scale is
         * max(v1.scale(), v2.scale()).
         *
         * @param v1 value 1.
         * @param v2 value 2.
         * @return a BigDecimal whose value is (v1 - v2).
         */
        fun subtract(v1: BigDecimal, v2: BigDecimal): BigDecimal =
                v1.subtract(v2)

        /**
         * Returns a BigDecimal whose value is (v1 × v2), and whose scale is
         * (v1.scale() + v2.scale()).
         *
         * @param v1 value 1.
         * @param v2 value 2.
         * @return Returns a BigDecimal whose value is (v1 × v2).
         */
        fun multiply(v1: BigDecimal, v2: BigDecimal): BigDecimal =
                v1.multiply(v2)


        /**
         * Returns a BigDecimal whose value is (v1 / v2), and whose scale is as specified.
         * If rounding must be performed to generate a result with the specified scale,
         * the specified rounding mode is applied.
         *
         * @param v1 value 1.
         * @param v2 value 2.
         * @return Returns a BigDecimal whose value is (v1 / v2).
         */
        fun divide(v1: BigDecimal, v2: BigDecimal): BigDecimal =
                v1.divide(v2, defaultScale, defaultRoundingMode)

    }
}
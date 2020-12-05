package br.com.hitg.domain.model

import java.math.BigDecimal

class CalculatorDisplayManager() {

    companion object {

        const val DECIMAL_SEPARATOR: Char = '.'
        const val NEGATIVE_SYMBOL: Char = '-'
    }

    private var internalDisplayText: StringBuilder = StringBuilder("0")
    private var usingDecimalSymbol: Boolean = false
    private var maxLength: Int = -1
    private var isValidNumber: Boolean = true

    constructor(maxLength: Int) : this(maxLength, "0")

    constructor(initialValue: String) : this(-1, initialValue)

    constructor(maxLength: Int, initialValue: String) : this() {
        require(!(maxLength < -1 || maxLength == 0)) { "The argument must be any number greater than zero or -1" }
        this.maxLength = maxLength

        setValue(initialValue)
    }

    fun appendNumber(charNumber: Char): Boolean {
        return appendNumber(charNumber, false)
    }

    fun appendNumber(charNumber: Char, replaceCurrentDisplay: Boolean): Boolean {
        require(Character.isDigit(charNumber)) { "Invalid value, must be a number" }

        val isZero: Boolean = internalDisplayText.length == 1
                && internalDisplayText.toString() == "0"


        if (charNumber == '0' && isZero) {
            return false
        } else if (!isValidNumber || replaceCurrentDisplay || isZero) {
            isValidNumber = true
            usingDecimalSymbol = false
            internalDisplayText.setLength(0)
        }
        if (canAppendChar()) {
            internalDisplayText.append(charNumber)
            return true
        }
        return false
    }

    private fun canAppendChar(): Boolean {
        if (maxLength > -1) {
            var realLength = internalDisplayText.length - if (usingDecimalSymbol) 1 else 0
            realLength -= if (internalDisplayText.indexOf(NEGATIVE_SYMBOL) > -1) 1 else 0
            return (maxLength > 0 && realLength < maxLength)
        }
        return true
    }

    override fun toString(): String {
        return internalDisplayText.toString()
    }

    fun toBigDecimal(): BigDecimal {
        return BigDecimal(toString())
    }

    fun appendDecimalSeparator(): Boolean {
        return appendDecimalSeparator(false)
    }

    fun appendDecimalSeparator(replaceCurrentDisplay: Boolean): Boolean {
        if (replaceCurrentDisplay) {
            setValue("0")
        }
        val isNegative = internalDisplayText.indexOf(NEGATIVE_SYMBOL) > -1
        val maxLengthAllowed = this.maxLength + if (isNegative) 1 else 0
        if (!usingDecimalSymbol && isValidNumber &&
                (this.maxLength == -1 ||
                        internalDisplayText.length < maxLengthAllowed)) {
            internalDisplayText.append(DECIMAL_SEPARATOR)
            usingDecimalSymbol = true
            return true
        }
        return false
    }

    fun setValue(value: String) {
        // Reset display value
        internalDisplayText.setLength(0)

        val indexOfDecimalSeparator = value.indexOf(DECIMAL_SEPARATOR)
        var maxLengthAllowed = this.maxLength
        if (indexOfDecimalSeparator > -1) {
            maxLengthAllowed++
        }
        if (value.indexOf(NEGATIVE_SYMBOL) > -1) {
            maxLengthAllowed++
        }

        isValidNumber = isNumber(value)
        if (isValidNumber
                && this.maxLength > -1
                && value.length > maxLengthAllowed) {
            if (indexOfDecimalSeparator == -1
                    || indexOfDecimalSeparator > this.maxLength) {
                throw ArithmeticException("The number has no precision enough")
            }
            if (indexOfDecimalSeparator == this.maxLength) {
                maxLengthAllowed--
            }
            internalDisplayText.append(value.substring(0, maxLengthAllowed))
        } else {
            internalDisplayText.append(value)
        }
        if (isValidNumber && isZero(internalDisplayText.toString())) {
            internalDisplayText.setLength(0)
            internalDisplayText.append("0")
        }
        if (isValidNumber) {
            usingDecimalSymbol = internalDisplayText.contains(DECIMAL_SEPARATOR)
        }
    }

    private fun isZero(value: String): Boolean {
        return try {
            BigDecimal("0").compareTo(BigDecimal(value)) == 0
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun isNumber(str: String): Boolean {
        try {
            BigDecimal(str)
        } catch (e: NumberFormatException) {
            return false
        }
        return true
    }

    fun removeLast(): Boolean {
        if (internalDisplayText.length == 1 && internalDisplayText.toString()[0] == '0') {
            return false
        }
        if (!isValidNumber) {
            internalDisplayText.setLength(0)
            isValidNumber = true
        }
        if (internalDisplayText.isNotEmpty()) {
            if (internalDisplayText.toString()[internalDisplayText.length - 1] == DECIMAL_SEPARATOR) {
                usingDecimalSymbol = false
            }
            internalDisplayText.setLength(internalDisplayText.length - 1)
            if (internalDisplayText.length == 1 &&
                    !Character.isDigit(internalDisplayText.toString()[0])) {
                internalDisplayText.setLength(0)
            }
        }
        if (internalDisplayText.isEmpty()) {
            internalDisplayText.append("0")
        }
        return true
    }
}
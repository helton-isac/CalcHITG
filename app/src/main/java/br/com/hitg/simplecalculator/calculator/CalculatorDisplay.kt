package br.com.hitg.simplecalculator.calculator

import java.math.BigDecimal

/**
 * Class to handle the value displayed on the calculator.
 */
class CalculatorDisplay() {

    companion object {
        /**
         * Decimal Symbol.
         */
        val DECIMAL_SEPARATOR: Char = '.'
    }

    /**
     * Internal control of the text to be displayed.
     */
    private var internalDisplayText: StringBuilder = StringBuilder("0")

    /**
     * Boolean value to indicate if the DecimalSymbol is in use or not.
     */
    private var usingDecimalSymbol: Boolean = false

    /**
     * Set the max size of input is allowed on this display
     * -1 means no limit
     */
    private var maxLength: Int = -1

    /**
     * Whether is a valid number or not
     */
    private var isValidNumber: Boolean = true

    /**
     * Constructor to allow set the max length of the Display
     *
     * @param maxLength An Int number to represent the max length of the Display.
     */
    constructor(maxLength: Int) : this(maxLength, "0")

    /**
     * Constructor to allow set an initial value to be displayed
     *
     * @param initialValue An initialValue to display
     */
    constructor(initialValue: String) : this(-1, initialValue)

    /**
     * Constructor to allow set the max length of the Display and an initial value to be displayed
     *
     * If the value is greater than maxLength, the value will be truncate.
     *
     * @param maxLength An Int number to represent the max length of the Display.
     * @param initialValue An initialValue to display
     */
    constructor(maxLength: Int, initialValue: String) : this() {
        if (maxLength < -1 || maxLength == 0) {
            throw IllegalArgumentException("The argument must be any number greater than zero or -1")
        }
        this.maxLength = maxLength

        setValue(initialValue)
    }

    /**
     * Appends a char on the last position of the display.
     * For example:
     *      current display: "123"
     *      input: '4'
     *      new display value = "123"+'4' = "1234"
     * @param charNumber Char input to be appended in the last position of the display
     */
    fun appendNumber(charNumber: Char): Boolean {
        return appendNumber(charNumber, false)
    }

    /**
     * Appends a char on the last position of the display.
     * For example:
     *      current display: "123"
     *      input: '4'
     *      new display value = "123"+'4' = "1234"
     * @param charNumber Char input to be appended in the last position of the display
     * @param replaceCurrentDisplay Boolean value, if true, the input will be the new Display
     * @returns True if the number was appended
     */
    fun appendNumber(charNumber: Char, replaceCurrentDisplay: Boolean): Boolean {
        if (!Character.isDigit(charNumber)) {
            throw IllegalArgumentException("Invalid value, must be a number")
        }

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

    /**
     * Check if can append char
     */
    private fun canAppendChar(): Boolean {
        val realLength = internalDisplayText.length - if (usingDecimalSymbol) 1 else 0
        return maxLength == -1 || (maxLength > 0 && realLength < maxLength)
    }

    /**
     * Returns the current Display as it is.
     */
    override fun toString(): String {
        return internalDisplayText.toString()
    }

    /**
     * Returns the current Display as a BigDecimal Object or null if it is not possible to parse.
     */
    fun toBigDecimal(): BigDecimal {
        return BigDecimal(toString())
    }

    /**
     * Appends a decimal separator. (Ignores MAX_LENGTH)
     *
     * It is only possible to append one separator
     *
     * @return Returns True if the Decimal Separator was appended
     */
    fun appendDecimalSeparator(): Boolean {
        return appendDecimalSeparator(false)
    }

    /**
     * Appends a decimal separator. (Ignores MAX_LENGTH)
     *
     * It is only possible to append one separator
     *
     * @param replaceCurrentDisplay Boolean value, if true, the input will be the new Display
     * @return Returns True if the Decimal Separator was appended
     */
    fun appendDecimalSeparator(replaceCurrentDisplay: Boolean): Boolean {
        if (replaceCurrentDisplay) {
            setValue("0")
        }
        if (!usingDecimalSymbol && isValidNumber) {
            internalDisplayText.append(DECIMAL_SEPARATOR)
            usingDecimalSymbol = true
            return true
        }
        return false
    }

    /**
     * Force a value change. Any text can be inserted here to be displayed.
     *
     * @param value to be set.
     */
    fun setValue(value: String) {
        internalDisplayText.setLength(0)

        val indexOfDecimalSeparator = value.indexOf(DECIMAL_SEPARATOR)
        val maxLengthConsideringDecimalSeparator =
                this.maxLength + if (indexOfDecimalSeparator > -1) 1 else 0

        isValidNumber = isNumber(value)
        if (isValidNumber
                && this.maxLength > -1
                && value.length > maxLengthConsideringDecimalSeparator) {
            if (indexOfDecimalSeparator == -1
                    || indexOfDecimalSeparator > this.maxLength) {
                throw ArithmeticException("The number has no precision enough")
            }
            internalDisplayText.append(value.substring(0, maxLengthConsideringDecimalSeparator))
        } else {
            internalDisplayText.append(value)
        }

        if (isValidNumber) {
            usingDecimalSymbol = internalDisplayText.contains(DECIMAL_SEPARATOR)
        }
    }

    /**
     * Check if the string is a valid BigDecimal.
     * @param str String to test.
     * @return Whether the String is a validBigDecimal or not.
     */
    private fun isNumber(str: String): Boolean {
        try {
            BigDecimal(str)
        } catch (e: NumberFormatException) {
            return false
        }
        return true
    }

    /**
     * Remove the last digit.
     *
     * @return Returns true if a digit was removed.
     */
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
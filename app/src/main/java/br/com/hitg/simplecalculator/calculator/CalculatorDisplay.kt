package br.com.hitg.simplecalculator.calculator

import java.math.BigDecimal

/**
 * Class to handle the value displayed on the calculator.
 */
class CalculatorDisplay() {

    /**
     * Decimal Symbol.
     */
    private val DECIMAL_SEPARATOR: Char = '.'

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
        if (maxLength < -1) {
            throw IllegalArgumentException("The argument must be any number between -1 and MAX_INTEGER")
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
    fun appendNumber(charNumber: Char) {
        appendNumber(charNumber, false)
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
        if (replaceCurrentDisplay || internalDisplayText.toString().equals("0")) {
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
        return maxLength == -1 || (maxLength > 0 && internalDisplayText.length < maxLength)
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
    fun toBigDecimal(): BigDecimal? {
        try {
            return BigDecimal(toString())
        } catch (e: NumberFormatException) {
            return null
        }
    }

    /**
     * Appends a decimal separator. (Ignores MAX_LENGTH)
     *
     * It is only possible to append one separator
     */
    fun appendDecimalSeparator(): Boolean {
        if (!usingDecimalSymbol) {
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
        if (this.maxLength > -1 && value.length > this.maxLength) {
            internalDisplayText.append(value.substring(0, this.maxLength))
        } else {
            internalDisplayText.append(value)
        }
    }

    fun removeLast() {
        if (internalDisplayText.length > 0) {
            internalDisplayText.setLength(internalDisplayText.length - 1);
            if (internalDisplayText.length == 1 &&
                    !Character.isDigit(internalDisplayText.toString()[0])) {
                internalDisplayText.setLength(0)
            }
        }
        if (internalDisplayText.length == 0) {
            internalDisplayText.append("0")
        }
    }
}
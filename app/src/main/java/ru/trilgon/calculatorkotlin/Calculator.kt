package ru.trilgon.calculatorkotlin

import java.math.BigDecimal
import java.util.*


class Calculator() : MainContract.Model {
    private val inValues: MutableList<String> = mutableListOf()

    override fun calculate(input: StringBuilder): String {
        prepareIn(input)

        var result: BigDecimal = (0).toBigDecimal()

        var index: Int = 0
        while (index <= inValues.lastIndex) {
            when {
                inValues[index] == "/" -> {
                    try {
                        result = inValues[index - 1].toBigDecimal()
                            .divide(inValues[index + 1].toBigDecimal())
                        inValues[index - 1] = result.toString()
                        inValues.subList(index, index + 2).clear()
                        index -= 2
                    } catch (e: ArithmeticException) {
                        inValues.clear()
                        return "Can't divide by zero"
                    }
                }
                inValues[index] == "*" -> {
                    result = inValues[index - 1].toBigDecimal() * inValues[index + 1].toBigDecimal()
                    inValues[index - 1] = result.toString()
                    inValues.subList(index, index + 2).clear()
                    index -= 2
                }
                inValues[index] == "%" -> {
                    try {
                        result =
                            inValues[index - 1].toBigDecimal() % inValues[index + 1].toBigDecimal()
                        inValues[index - 1] = result.toString()
                        inValues.subList(index, index + 2).clear()
                        index -= 2
                    } catch (e: ArithmeticException) {
                        inValues.clear()
                        return "Can't divide by zero"
                    }
                }
            }
            index++
        }

        index = 0
        while (index <= inValues.lastIndex) {
            when {
                inValues[index] == "+" -> {
                    result = inValues[index - 1].toBigDecimal() + inValues[index + 1].toBigDecimal()
                    inValues[index - 1] = result.toString()
                    inValues.subList(index, index + 2).clear()
                    index -= 2
                }
                inValues[index] == "-" -> {
                    result = inValues[index - 1].toBigDecimal() - inValues[index + 1].toBigDecimal()
                    inValues[index - 1] = result.toString()
                    inValues.subList(index, index + 2).clear()
                    index -= 2
                }
            }
            index++
        }
        if (result == 0.toBigDecimal() && inValues.isNotEmpty())
            result = inValues[0].toBigDecimal()
        inValues.clear()

        return try {
            result.intValueExact().toString()
        } catch (e: ArithmeticException) {
            result.toString()
        }
    }

    private fun prepareIn(input: StringBuilder) {
        var numDetected = false
        var startIndex = 0
        val trimmedIn = input.filter { !it.isWhitespace() }

        for (i in 0..trimmedIn.lastIndex) {
            when {
                trimmedIn[i].isDigit() && !numDetected -> {
                    startIndex = i
                    numDetected = true
                }
                !trimmedIn[i].isDigit() && numDetected && trimmedIn[i] != '.' -> {
                    inValues.add(trimmedIn.substring(startIndex, i))
                    inValues.add(trimmedIn[i].toString())
                    numDetected = false
                }
                !trimmedIn[i].isDigit() && !numDetected && trimmedIn[i] == '-' -> {
                    startIndex = i
                    numDetected = true
                }
            }
        }
        if (numDetected) {
            inValues.add(trimmedIn.substring(startIndex..trimmedIn.lastIndex))
        }
    }
}
package ru.trilgon.calculatorkotlin

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var input: StringBuilder = StringBuilder("")
    private var canOperation = false
    private var wasPoint = false
    private val calculator: MainContract.Model = Calculator()

    override fun onClickNumber(buttonId: Int) {
        when (buttonId) {
            R.id.btnZero -> {
                if (input.isEmpty() || input[0].toChar() != '0' || input.length != 1) {
                    numberClicked(0)
                }
            }
            R.id.btnOne -> {
                checkZero()
                numberClicked(1)
            }
            R.id.btnTwo -> {
                checkZero()
                numberClicked(2)
            }
            R.id.btnThree -> {
                checkZero()
                numberClicked(3)
            }
            R.id.btnFour -> {
                checkZero()
                numberClicked(4)
            }
            R.id.btnFive -> {
                checkZero()
                numberClicked(5)
            }
            R.id.btnSix -> {
                checkZero()
                numberClicked(6)
            }
            R.id.btnSeven -> {
                checkZero()
                numberClicked(7)
            }
            R.id.btnEight -> {
                checkZero()
                numberClicked(8)
            }
            R.id.btnNine -> {
                checkZero()
                numberClicked(9)
            }
        }
    }

    private fun checkZero() {
        if (input.isNotEmpty() && input[0].toChar() == '0' && input.length == 1) input.clear()
    }

    private fun numberClicked(number: Int) {
        input.append(number)
        canOperation = true
        view.showInput(input.toString())
        view.showResult(calculator.calculate(input))
    }

    private fun operationClicked(operation: String) {
        input.append(" $operation ")
        canOperation = false
        wasPoint = false
        view.showInput(input.toString())
    }

    override fun onClickAction(buttonId: Int) {
        if (canOperation) {
            when (buttonId) {
                R.id.btnPlus -> {
                    operationClicked("+")
                }
                R.id.btnMinus -> {
                    operationClicked("-")
                }
                R.id.btnMultiply -> {
                    operationClicked("*")
                }
                R.id.btnDivide -> {
                    operationClicked("/")
                }
                R.id.btnMod -> {
                    operationClicked("%")
                }
            }
        }

        when (buttonId) {
            R.id.btnPoint -> {
                if (!wasPoint) {
                    if (input.isEmpty() || !input.last().isDigit()) {
                        input.append("0.")
                        view.showInput(input.toString())
                    } else {
                        input.append('.')
                        view.showInput(input.toString())
                    }
                    wasPoint = true
                } else {
                    view.showMessage(view.getStringRes((R.string.warn_point_was)))
                }
            }
            R.id.btnSign -> {
                if (input.isNotEmpty() && input.last().isDigit()) {
                    for (i in input.lastIndex downTo 0) {
                        if (i == 0 && input[i].isDigit()) {
                            input.insert(0, '-')
                            view.showInput(input.toString())
                            view.showResult(calculator.calculate(input))
                            break
                        }
                        if (!input[i].isDigit()) {
                            if (i == 0) {
                                input.deleteCharAt(0)
                                view.showInput(input.toString())
                                view.showResult(calculator.calculate(input))
                                break
                            }
                            if (input[i] == '-') {
                                input.deleteCharAt(i)
                                view.showInput(input.toString())
                                view.showResult(calculator.calculate(input))
                                break
                            }
                            if (input[i] != '.') {
                                input.insert(i + 1, '-')
                                view.showInput(input.toString())
                                view.showResult(calculator.calculate(input))
                                break
                            }
                        }
                    }
                }
            }
            R.id.btnClear -> {
                input.clear()
                view.showInput(input.toString())
                view.showResult("")
                wasPoint = false
                canOperation = false
            }
            R.id.btnEquals -> {
                if (input.isNotEmpty() && input.last().isDigit()) {
                    val result = calculator.calculate(input)
                    wasPoint = if (result != "Can't divide by zero"){
                        input.replace(0, input.length, result)
                        view.showInput(input.toString())
                        result.contains('.')
                    } else{
                        input.clear()
                        view.showInput(input.toString())
                        view.showResult("")
                        false
                    }
                } else view.showMessage(view.getStringRes(R.string.warn_wrong_input))
            }
        }
    }

}
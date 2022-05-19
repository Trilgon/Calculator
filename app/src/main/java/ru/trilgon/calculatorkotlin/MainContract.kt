package ru.trilgon.calculatorkotlin

interface MainContract {
    interface Model {
        fun calculate(input: StringBuilder): String
    }

    interface Presenter {
        fun onClickNumber(buttonId: Int)
        fun onClickAction(buttonId: Int)
    }

    interface View {
        fun showInput(input: String)
        fun showResult(result: String)
        fun showMessage(message: String)
        fun getStringRes(stringId: Int): String
    }
}
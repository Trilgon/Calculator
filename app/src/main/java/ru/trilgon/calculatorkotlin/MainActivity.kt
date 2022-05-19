package ru.trilgon.calculatorkotlin

import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.trilgon.calculatorkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var biding: ActivityMainBinding
    private lateinit var vibrator: Vibrator
    private val presenter: MainContract.Presenter = MainPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)
        @Suppress("DEPRECATION")
        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        biding.apply {
            btnZero.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnOne.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnTwo.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnThree.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnFour.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnFive.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnSix.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnSeven.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnEight.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }
            btnNine.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickNumber(it.id)
            }

            btnPlus.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
            btnMinus.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
            btnMultiply.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
            btnDivide.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
            btnMod.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
            btnClear.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
            btnSign.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
            btnPoint.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
            btnEquals.setOnClickListener {
                vibrator.vibrate(40)
                presenter.onClickAction(it.id)
            }
        }
    }

    override fun showInput(input: String) {
        biding.textIn.text = input
    }

    override fun showResult(result: String) {
        biding.textOut.text = result
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun getStringRes(stringId: Int): String {
        return getString(stringId)
    }
}
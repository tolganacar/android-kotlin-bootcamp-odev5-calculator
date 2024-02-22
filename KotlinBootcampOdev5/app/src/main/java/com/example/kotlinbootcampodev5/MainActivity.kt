package com.example.kotlinbootcampodev5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kotlinbootcampodev5.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var numberString: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        numberClicked(binding.button0, 0)
        numberClicked(binding.button1, 1)
        numberClicked(binding.button2, 2)
        numberClicked(binding.button3, 3)
        numberClicked(binding.button4, 4)
        numberClicked(binding.button5, 5)
        numberClicked(binding.button6, 6)
        numberClicked(binding.button7, 7)
        numberClicked(binding.button8, 8)
        numberClicked(binding.button9, 9)

        buttonSumClicked()
        buttonMinusClicked()
        buttonMultiplyClicked()
        buttonDivideClicked()

        buttonBackspaceClicked()

        buttonResultClicked()

        buttonResetClicked()
    }

    private fun numberClicked(button: Button, sayi: Int) {
        button.setOnClickListener {
            if (binding.textViewResult.text.equals("0")) {
                numberString = sayi.toString()
                binding.textViewResult.text = numberString
            } else {
                updateTextView(sayi.toString())
            }
        }
    }

    private fun updateTextView(value: String) {
        if (numberString.length < 9) {
            numberString += value
            binding.textViewResult.text = numberString
        } else {
            Snackbar.make(
                findViewById(android.R.id.content),
                "Daha fazla sayı giremezsiniz.",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun buttonSumClicked() {
        binding.buttonSum.setOnClickListener {
            if (!(numberString.contains("-") || numberString.contains("×") || numberString.contains("÷"))) {
                operatorButtonClicked("+")
            }
        }
    }

    private fun buttonMinusClicked() {
        binding.buttonMinus.setOnClickListener {
            if (!(numberString.contains("+") || numberString.contains("×") || numberString.contains("÷"))) {
                operatorButtonClicked("-")
            }
        }
    }

    private fun buttonMultiplyClicked() {
        binding.buttonMultiply.setOnClickListener {
            if (!(numberString.contains("+") || numberString.contains("-") || numberString.contains("÷"))) {
                operatorButtonClicked("×")
            }
        }
    }

    private fun buttonDivideClicked() {
        binding.buttonDivide.setOnClickListener {
            if (!(numberString.contains("+") || numberString.contains("-") || numberString.contains("×"))) {
                operatorButtonClicked("÷")
            }
        }
    }

    private fun operatorButtonClicked(operator: String) {
        if (!binding.textViewResult.text.endsWith(operator)) {
            updateTextView(operator)
        }
    }

    private fun buttonBackspaceClicked() {
        binding.buttonBackspace.setOnClickListener {
            if (numberString.length == 1) {
                numberString = 0.toString()
                binding.textViewResult.text = numberString
            } else {
                numberString = numberString.dropLast(1)
                binding.textViewResult.text = numberString
            }
        }
    }

    private fun buttonResultClicked() {
        binding.buttonResult.setOnClickListener {
            if (numberString.contains("+")) {
                operatorFun("+")
            } else if (numberString.contains("-")) {
                operatorFun("-")
            } else if (numberString.contains("×")) {
                operatorFun("×")
            } else if (numberString.contains("÷")) {
                operatorFun("÷")
            }
        }
    }

    private fun operatorFun(operator: String) {
        if (numberString.endsWith(operator)) {
            numberString = numberString.dropLast(1)
            operate(operator)
        } else {
            operate(operator)
        }
    }

    private fun operate(operator: String) {
        val numbers = numberString.split(operator)
        when (operator) {
            "+" -> {
                var result = 0
                for (i in numbers) {
                    result += i.toInt()
                }
                showResult(result)
            }

            "-" -> {
                var result = numbers.first().toInt()
                for (i in numbers.drop(1)) {
                    result -= i.toInt()
                }
                showResult(result)
            }

            "×" -> {
                var result = 1
                for (i in numbers) {
                    result *= i.toInt()
                }
                showResult(result)
            }

            "÷" -> {
                var result = numbers.first().toInt()
                for (i in numbers.drop(1)) {
                    result /= i.toInt()
                }
                showResult(result)
            }
        }
    }

    private fun showResult(result: Int) {
        numberString = result.toString()
        binding.textViewResult.text = numberString
    }

    private fun buttonResetClicked() {
        binding.buttonReset.setOnClickListener {
            binding.textViewResult.text = "0"
            numberString = "0"
        }
    }
}
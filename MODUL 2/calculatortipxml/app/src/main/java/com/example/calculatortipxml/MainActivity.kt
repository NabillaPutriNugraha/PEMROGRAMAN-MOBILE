package com.example.calculatortipxml

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var etBillAmount: TextInputEditText
    private lateinit var actvTipPercentage: AutoCompleteTextView
    private lateinit var switchRoundUp: SwitchMaterial
    private lateinit var tvTipAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBillAmount = findViewById(R.id.etBillAmount)
        actvTipPercentage = findViewById(R.id.actvTipPercentage)
        switchRoundUp = findViewById(R.id.switchRoundUp)
        tvTipAmount = findViewById(R.id.tvTipAmount)

        val tipOptions = resources.getStringArray(R.array.tip_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipOptions)

        actvTipPercentage.setAdapter(adapter)

        actvTipPercentage.setOnClickListener {
            actvTipPercentage.showDropDown()
        }

        etBillAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                calculateAndDisplayTip()
            }
        })

        actvTipPercentage.setOnItemClickListener { _, _, _, _ ->
            calculateAndDisplayTip()
        }

        switchRoundUp.setOnCheckedChangeListener { _, _ ->
            calculateAndDisplayTip()
        }
    }

    private fun calculateAndDisplayTip() {
        val stringInTextField = etBillAmount.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            tvTipAmount.text = getString(R.string.tip_amount_default)
            return
        }

        val tipPercentageString = actvTipPercentage.text.toString()
        val tipPercentage = tipPercentageString.replace("%", "").toDoubleOrNull() ?: 15.0
        val tipDecimal = tipPercentage / 100.0

        var tip = cost * tipDecimal

        if (switchRoundUp.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tvTipAmount.text = getString(R.string.tip_amount_result, formattedTip)
    }
}
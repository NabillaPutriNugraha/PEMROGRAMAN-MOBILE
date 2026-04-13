package com.example.tipcalculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                TipCalculatorApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculatorApp() {

    var billAmountInput by rememberSaveable { mutableStateOf("") }
    var tipPercentage by rememberSaveable { mutableIntStateOf(15) }
    var roundUp by rememberSaveable { mutableStateOf(false) }
    var expanded by rememberSaveable { mutableStateOf(false) }

    val tipOptions = listOf(15, 18, 20)

    val billAmount = billAmountInput.toDoubleOrNull() ?: 0.0
    var tip = billAmount * (tipPercentage / 100.0)

    if (roundUp) tip = ceil(tip)

    val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = stringResource(R.string.calculate_tip),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = billAmountInput,
                onValueChange = {
                    billAmountInput = it.filter { ch -> ch.isDigit() || ch == '.' }
                },
                label = { Text(stringResource(R.string.bill_amount)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_money),
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = "$tipPercentage%",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(R.string.tip_percentage)) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_percent),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    tipOptions.forEach {
                        DropdownMenuItem(
                            text = { Text("$it%") },
                            onClick = {
                                tipPercentage = it
                                expanded = false
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.round_up_tip))
                Switch(
                    checked = roundUp,
                    onCheckedChange = { roundUp = it }
                )
            }

            Text(
                text = stringResource(R.string.tip_amount, formattedTip),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
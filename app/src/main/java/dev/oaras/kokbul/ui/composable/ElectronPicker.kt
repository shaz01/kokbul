/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


enum class Ion(val label: String, val superscript: String) {
    Plus1("+1", "⁺¹"),
    Plus2("+2", "⁺²"),
    Plus3("+3", "⁺³"),
    Plus1or2("+1 / +2", "⁺¹ / ⁺²"),
    Plus2or3("+2 / +3", "⁺² / ⁺³"),
    Plus2or4("+2 / +4", "⁺² / ⁺⁴"),
    Minus1("-1", "⁻¹"), Minus2("-2", "⁻²"), Minus3("-3", "⁻³");

    companion object {
        val values by lazy { Ion.values() }
        val size by lazy { values.size }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AnswerIcon(active: Boolean, correct: Boolean) {
    SegmentedButtonDefaults.SegmentedButtonIcon(
        active = active,
        activeContent = {
            Icon(
                imageVector = if (correct)
                    Icons.Filled.Check
                else
                    Icons.Filled.Close,
                contentDescription = null,
                modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectronPicker(
    modifier: Modifier = Modifier,
    subModifier: Modifier = Modifier,
    onPick: (Ion) -> Unit,
    attempts: List<Ion>,
    correctAnswer: Ion
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        MultiChoiceSegmentedButtonRow(modifier = subModifier) {
            Ion.values.slice(0..2).forEachIndexed { index, ion ->
                val checked = attempts.contains(ion)
                SegmentedButton(
                    shape = SegmentedButtonDefaults.shape(position = index, count = 3),
                    onCheckedChange = { onPick(ion) },
                    checked = checked,
                    icon = { AnswerIcon(active = checked, correct = ion == correctAnswer) },
                    content = { Text(text = ion.label) }
                )
            }
        }
        MultiChoiceSegmentedButtonRow(modifier = subModifier) {
            Ion.values.slice(3..5).forEachIndexed { index, ion ->
                val checked = attempts.contains(ion)
                SegmentedButton(
                    shape = SegmentedButtonDefaults.shape(position = index, count = 3),
                    onCheckedChange = { onPick(ion) },
                    icon = { AnswerIcon(active = checked, correct = ion == correctAnswer) },
                    checked = checked,
                    content = { Text(text = ion.label) }
                )
            }
        }
        MultiChoiceSegmentedButtonRow(modifier = subModifier) {
            Ion.values.slice(6..8).forEachIndexed { index, ion ->
                val checked = attempts.contains(ion)
                SegmentedButton(
                    shape = SegmentedButtonDefaults.shape(position = index, count = 3),
                    onCheckedChange = { onPick(ion) },
                    icon = { AnswerIcon(active = checked, correct = ion == correctAnswer) },
                    checked = checked,
                    content = { Text(text = ion.label) }
                )
            }
        }
    }
}

@Preview
@Composable
fun ElectronPickerPreview() {
    ElectronPicker(
        Modifier,
        Modifier
            .fillMaxWidth()
            .padding(16.dp), {}, listOf(Ion.Plus2, Ion.Minus3, Ion.Plus2or4), Ion.Plus2or4
    )
}
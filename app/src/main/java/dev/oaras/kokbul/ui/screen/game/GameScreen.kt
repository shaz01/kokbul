/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.screen.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.oaras.kokbul.R
import dev.oaras.kokbul.ui.composable.ElectronPicker
import dev.oaras.kokbul.ui.composable.Ion
import dev.oaras.kokbul.ui.composable.IonView

@Composable
fun GameScreen(viewModel: GameViewModel) {
    val nextEnabled by viewModel.nextEnabled.collectAsState(initial = false)
    val pickEnabled by viewModel.pickEnabled.collectAsState(initial = false)
    val attempts by viewModel.attempts.collectAsState()
    val correctAnswer by viewModel.correctAnswer.collectAsState()
    val pair by viewModel.name.collectAsState()
    val ions by viewModel.ions.collectAsState()
    GameScreen(
        name = pair.first,
        knownAs = pair.second,
        hideContent = ions.isEmpty(),
        onClickBack = viewModel::onClickBack,
        nextEnabled = nextEnabled,
        next = viewModel::next,
        attempts = attempts,
        correctAnswer = correctAnswer,
        onPick = { if (pickEnabled) viewModel.pick(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    name: String,
    knownAs: String,
    hideContent: Boolean,
    onClickBack: () -> Unit,
    nextEnabled: Boolean,
    next: () -> Unit,
    attempts: List<Ion>,
    correctAnswer: Ion,
    onPick: (Ion) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    FilledTonalIconButton(
                        onClick = onClickBack,
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        },
        content = { padding ->
            if (!hideContent) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(0.3f))
                    IonView(name = name, knownAs = knownAs)
                    Spacer(Modifier.weight(0.5f))
                    ElectronPicker(
                        attempts = attempts,
                        correctAnswer = correctAnswer,
                        subModifier = Modifier.fillMaxWidth(),
                        onPick = onPick
                    )
                    Spacer(Modifier.weight(0.08f))
                    FilledTonalButton(
                        onClick = next,
                        Modifier
                            .padding(16.dp)
                            .align(Alignment.End),
                        enabled = nextEnabled
                    ) {
                        Text(text = stringResource(R.string.next))
                    }
                    Spacer(Modifier.weight(0.12f))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(
        name = "NO3",
        knownAs = "Nitrat",
        hideContent = false,
        onClickBack = {},
        nextEnabled = false,
        onPick = {},
        next = {},
        attempts = listOf(Ion.Plus2, Ion.Minus3, Ion.Plus2or3),
        correctAnswer = Ion.Plus2or4,
    )
}
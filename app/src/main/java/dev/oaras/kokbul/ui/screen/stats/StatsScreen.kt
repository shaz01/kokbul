/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.screen.stats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
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
import dev.oaras.kokbul.R
import dev.oaras.kokbul.db.IonRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(vm: StatsViewModel) {
    val state by vm.state.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = vm::backClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = { Text(text = stringResource(id = R.string.stats)) }
            )
        }
    ) { cp ->
        when (state) {
            StatsViewModel.State.Loading -> {
                LoadingScreen(cp)
            }

            is StatsViewModel.State.Ready -> {
                val state: StatsViewModel.State.Ready = state as StatsViewModel.State.Ready
                StatsScreen(ionStats = state.list, cp)
            }
        }
    }
}

@Composable
fun LoadingScreen(cp: PaddingValues) {
    Box(
        Modifier
            .padding(cp)
            .fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun StatsScreen(ionStats: List<IonRepository.IonStats>, contentPadding: PaddingValues) {
    LazyColumn(contentPadding = contentPadding) {
        items(ionStats) { ion ->
            IonStat(
                modifier = Modifier.fillMaxWidth(),
                name = ion.ionData.name,
                knownAs = ion.ionData.knownAs,
                correct = ion.timesCorrect,
                incorrect = ion.timesIncorrect
            )
        }
    }
}

@Composable
fun IonStat(
    modifier: Modifier,
    name: String,
    knownAs: String,
    correct: Int,
    incorrect: Int,
) {
    ListItem(
        modifier = modifier,
        headlineContent = { Text(text = name) },
        overlineContent = { Text(text = knownAs) },
        supportingContent = {
            Text(
                text = stringResource(
                    id = R.string.stats_expl,
                    correct,
                    incorrect,
                    correct + incorrect
                )
            )
        },
    )

}

@Preview
@Composable
fun IonStatPreview() {
    IonStat(
        modifier = Modifier.fillMaxWidth(),
        name = "H20",
        knownAs = "Water",
        correct = 5,
        incorrect = 12
    )
}
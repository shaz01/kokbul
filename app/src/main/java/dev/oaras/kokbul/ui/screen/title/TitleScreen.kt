/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.screen.title


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.oaras.kokbul.R
import dev.oaras.kokbul.ui.composable.IonShowcase


@Composable
fun TitleScreen(titleViewModel: TitleViewModel) {
    val context = LocalContext.current
    TitleScreen(
        title = stringResource(id = R.string.app_name),
        onClickPlay = titleViewModel::onClickPlay,
        onClickStats = titleViewModel::onClickStats,
        onClickGithub = { titleViewModel.onClickGithub(context) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleScreen(
    title: String,
    onClickPlay: () -> Unit,
    onClickStats: () -> Unit,
    onClickGithub: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = title) })
        },
        content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
            ) {
                Spacer(modifier = Modifier.weight(0.30f))
                IonShowcase(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.weight(0.5f))
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClickPlay
                ) {
                    Text(text = stringResource(id = R.string.play))
                }
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClickStats,
                    enabled = true
                ) {
                    Text(text = stringResource(id = R.string.stats))
                }
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClickGithub,
                    enabled = true
                ) {
                    Text(text = stringResource(id = R.string.github))
                }
                Spacer(modifier = Modifier.weight(0.15f))
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun TitleScreenPreview() {
    TitleScreen(
        title = stringResource(id = R.string.app_name),
        onClickPlay = {},
        onClickStats = {},
        onClickGithub = {},
    )
}
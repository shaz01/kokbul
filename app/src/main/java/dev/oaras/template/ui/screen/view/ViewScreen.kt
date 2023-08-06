/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui.screen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import dev.oaras.template.R
import dev.oaras.template.utils.Quote

@Composable
fun ViewScreen(viewModel: ViewViewModel) {
    val loading by viewModel.loading.collectAsState(initial = true)
    val value by viewModel.content.collectAsState(initial = null)
    ViewScreen(
        value = value ?: "",
        loading = loading,
        onClickBack = viewModel::onClickBack,
        onClickEdit = viewModel::onClickEdit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScreen(
    value: String,
    loading: Boolean,
    onClickBack: () -> Unit,
    onClickEdit: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onClickBack,
                        content = {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    )
                },
                actions = {
                    IconButton(onClick = onClickEdit) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = stringResource(id = R.string.edit)
                        )
                    }
                },
                title = {}
            )
        },
        content = { padding ->
            if (loading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(padding)
            ) {
                Text(text = value)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditScreenLoadingPreview() {
    ViewScreen(
        value = Quote.RollerCoaster,
        loading = true,
        onClickBack = {},
        onClickEdit = {},
    )
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    ViewScreen(
        value = Quote.RollerCoaster,
        loading = false,
        onClickBack = {},
        onClickEdit = {},
    )
}

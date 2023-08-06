/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui.screen.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
fun EditScreen(viewModel: EditViewModel) {
    val loading by viewModel.loading.collectAsState(initial = true)
    val value by viewModel.content.collectAsState()
    val buttonEnabled by viewModel.buttonEnabled.collectAsState(initial = true)
    EditScreen(
        value = value ?: "",
        onValueChange = viewModel::onChangeDraft,
        buttonEnabled = buttonEnabled,
        loading = loading,
        addDraft = viewModel::add,
        onClickBack = viewModel::onClickBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    value: String,
    onValueChange: (String) -> Unit,
    buttonEnabled: Boolean,
    loading: Boolean,
    addDraft: () -> Unit,
    onClickBack: () -> Unit
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
                title = { Text(text = stringResource(id = R.string.edit_title)) }
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
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    onValueChange = onValueChange
                )
                Spacer(Modifier.height(16.dp))
                ElevatedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = addDraft,
                    enabled = buttonEnabled
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.padding(4.dp),
                            imageVector = Icons.Default.Save,
                            contentDescription = null
                        )
                        Text(text = stringResource(id = R.string.save_draft_button))
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditScreenLoadingPreview() {
    EditScreen(
        value = Quote.RollerCoaster,
        onValueChange = {},
        buttonEnabled = true,
        loading = true,
        addDraft = {},
        onClickBack = {}
    )
}
@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    EditScreen(
        value = Quote.RollerCoaster,
        onValueChange = {},
        buttonEnabled = true,
        loading = false,
        addDraft = {},
        onClickBack = {}
    )
}
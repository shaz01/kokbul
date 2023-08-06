/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.oaras.template.R
import dev.oaras.template.utils.Quote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraftItem(
    modifier: Modifier = Modifier,
    time: String,
    draft: String,
    delete: () -> Unit,
    edit: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    ListItem(
        modifier = modifier,
        headlineText = { Text(text = draft) },
        supportingText = { Text(text = time) },
        trailingContent = {
            IconButton(
                onClick = { expanded = true },
                content = { Icon(Icons.Default.MoreVert, null) }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text(text = stringResource(R.string.delete)) },
                    onClick = delete
                )
                DropdownMenuItem(
                    text = { Text(text = stringResource(R.string.edit)) },
                    onClick = edit
                )
            }
        }
    )
}

@Preview
@Composable
fun DraftItemPreview() {
    DraftItem(
        time = "20.05.2006",
        draft = Quote.RollerCoaster,
        delete = {}, edit = {}
    )
}
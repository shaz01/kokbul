/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Textsms
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.oaras.template.R
import dev.oaras.template.db.Draft
import dev.oaras.template.ui.composable.DraftItem
import dev.oaras.template.utils.Quote
import dev.oaras.template.utils.toTimeString


@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val drafts = mainViewModel.drafts.collectAsLazyPagingItems()
    MainScreen(
        title = stringResource(id = R.string.app_name),
        onClickAdd = mainViewModel::onClickAdd,
        content = { paddingValues ->
            item { Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding())) }
            items(drafts, key = Draft::id) { draft ->
                if (draft != null) {
                    DraftItem(
                        modifier = Modifier.clickable { mainViewModel.view(draft) },
                        time = draft.time.toTimeString(),
                        draft = draft.draft,
                        delete = { mainViewModel.delete(draft) },
                        edit = { mainViewModel.edit(draft) }
                    )
                    Divider(Modifier.fillMaxWidth())
                }
            }
            item { Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding())) }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    title: String,
    onClickAdd: () -> Unit,
    content: LazyListScope.(PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = title) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClickAdd) {
                Icon(Icons.Default.Textsms, null)
            }
        },
        content = { paddingValues ->
            LazyColumn(Modifier.fillMaxSize(), content = { content(paddingValues) })
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        title = stringResource(id = R.string.app_name),
        onClickAdd = {},
        content = {
            items(50) { i ->
                DraftItem(
                    time = (i * 100_000).toLong().toTimeString(),
                    draft = Quote.RollerCoaster,
                    delete = {}, edit = {}
                )
            }
        }
    )
}
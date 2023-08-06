/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun About(modifier: Modifier) {
    ElevatedCard(
        modifier = modifier,
    ) {
        Column {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription =
//            )
        }
    }

}

@Preview
@Composable
fun AboutPreview() {
    About(Modifier.fillMaxWidth())
}
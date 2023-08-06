/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.oaras.kokbul.db.IonData
import dev.oaras.kokbul.ui.theme.Typography


@Composable
fun IonView(modifier: Modifier, ionData: IonData, showIon: Boolean) {
    IonView(modifier = modifier, ionData.name, ionData.knownAs, if (showIon) ionData.ion else null)
}

@Composable
fun IonView(modifier: Modifier = Modifier, name: String, knownAs: String, ion: Ion? = null) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = if (ion != null) "$name${ion.superscript}" else name,
            style = Typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = knownAs,
            style = Typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}
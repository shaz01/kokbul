/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.screen.title

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.oaras.kokbul.BaseViewModel
import dev.oaras.kokbul.navigation.routing.generatePath
import dev.oaras.kokbul.ui.Routes
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TitleViewModel @Inject constructor() : BaseViewModel() {
    fun onClickPlay() = viewModelScope.launch {
        navigateTo(Routes.Game.generatePath())
    }

    fun onClickStats() = viewModelScope.launch {
        navigateTo(Routes.Stats.generatePath())
    }

    fun onClickGithub(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW).setData(
            Uri.parse("https://github.com/shaz01/kokbul/")
        )
        context.startActivity(intent)
    }
}
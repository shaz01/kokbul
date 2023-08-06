/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.di.entrypoint

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dev.oaras.kokbul.di.component.ComposableComponent

@InstallIn(ComposableComponent::class)
@EntryPoint
interface ComposableEntryPoint
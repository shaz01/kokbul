/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.di.entrypoint

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.oaras.kokbul.db.IonDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton @Provides
    fun provideDatabase(@ApplicationContext ctx: Context) = IonDatabase.build(ctx)

    @Singleton @Provides
    fun provideDao(db: IonDatabase) = db.ionDao()

    @Singleton @Provides
    fun provideAnswerDao(db: IonDatabase) = db.answerDao()
}
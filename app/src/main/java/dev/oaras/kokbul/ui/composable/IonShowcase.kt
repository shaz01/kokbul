/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.ui.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.oaras.kokbul.db.DATA_SHUFFLED
import dev.oaras.kokbul.db.IonData


@Composable
fun IonShowcase(
    modifier: Modifier,
    ions: List<IonData> = DATA_SHUFFLED,
) {
    if (ions.isNotEmpty()) {
        val transition = rememberInfiniteTransition(label = "ionShowcaseIT")
        val animation by transition.animateValue(
            initialValue = 0,
            targetValue = ions.lastIndex,
            typeConverter = Int.VectorConverter,
            animationSpec = InfiniteRepeatableSpec(
                animation = TweenSpec(ions.size * 1250, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "ionShowcaseInt"
        )
        AnimatedContent(targetState = animation, label = "ionShowcaseAC") { index ->
            val ionData = ions[index]
            IonView(modifier, ionData, true)
        }
    }
}
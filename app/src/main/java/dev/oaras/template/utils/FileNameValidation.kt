/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.template.utils

private val ILLEGAL_CHARACTERS = setOf(
    '/',
    '\n',
    '\r',
    '\t',
    '\u0000',
    '\u000c',
    '`',
    '?',
    '*',
    '\\',
    '<',
    '>',
    '|',
    '\'',
    ':'
)

//TODO check which one is faster
fun String.isValidFileName(): Boolean {
    return !containsAny(ILLEGAL_CHARACTERS)
}

@Suppress("NOTHING_TO_INLINE")
inline fun String.containsAny(characters: Set<Char>): Boolean {
    return this.any { it in characters }
}

fun validateFileNameFast(name: String): Boolean{
    val first = name.toSet()
    return first.subtract(ILLEGAL_CHARACTERS) == first
}
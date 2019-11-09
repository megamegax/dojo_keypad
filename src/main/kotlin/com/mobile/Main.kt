package com.mobile

import InputProcessor
import com.mobile.service.DictionaryLookup
import com.mobile.service.DictionaryParser
import com.mobile.service.FileReader

fun main() {
    val app = App(InputProcessor(), DictionaryLookup(DictionaryParser(), FileReader()))
}

open class App(
    private val inputProcessor: InputProcessor,
    private val dictionaryLookup: DictionaryLookup
) {

    fun setup(dictionaryPath: String) {
        dictionaryLookup.load(dictionaryPath)
    }

    fun process(input: String): String {
        return dictionaryLookup.lookup(inputProcessor.process(input)).joinToString(separator = " ")
    }
}
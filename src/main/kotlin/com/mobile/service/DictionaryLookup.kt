package com.mobile.service

open class DictionaryLookup(
    private val dictionaryParser: DictionaryParser,
    private val fileReader: FileReader
) {
    private lateinit var dictionary: List<String>

    open fun load(dictionaryPath: String) {
        val rawText = fileReader.readFile(dictionaryPath)
        dictionary = dictionaryParser.parse(rawText)
    }

    open fun lookup(input: String): List<String> {
        return dictionary.filter { it.startsWith(input) }
    }
}
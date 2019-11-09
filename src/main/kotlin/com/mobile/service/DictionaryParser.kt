package com.mobile.service

open class DictionaryParser {
    open fun parse(text: String): List<String> {
        return text.split(System.lineSeparator())
    }
}
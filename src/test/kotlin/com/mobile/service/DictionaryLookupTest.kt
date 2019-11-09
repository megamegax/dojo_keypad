package com.mobile.service

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class DictionaryLookupTest : StringSpec({
    "setup should read dictionary"{
        val dictionaryString = "raw string"
        val dictionaryPath = "path"
        val input = "22 33"
        val expectedResult = "BE"
        val mockDictionaryParser = mock<DictionaryParser>()
        val mockFileReader = mock<FileReader> { on { readFile(dictionaryPath) } doReturn dictionaryString }

        val app = DictionaryLookup(mockDictionaryParser, mockFileReader)

        app.load(dictionaryPath)

        verify(mockFileReader).readFile(dictionaryPath)

    }
    "setup should parse raw text"{
        val dictionaryString = "raw string"
        val dictionaryPath = "path"
        val input = "22 33"
        val expectedResult = "BE"
        val mockFileReader = mock<FileReader> { on { readFile(dictionaryPath) } doReturn dictionaryString }
        val mockDictionaryParser =
            mock<DictionaryParser> { on { parse(dictionaryString) } doReturn listOf("parsed") }

        val app = DictionaryLookup(mockDictionaryParser, mockFileReader)

        app.load(dictionaryPath)

        verify(mockDictionaryParser).parse(dictionaryString)

    }
    "lookup should filter words from the dictionary" {
        val listOfWords = listOf("abc", "bcd")
        val dictionaryString = "raw string"
        val dictionaryPath = "path"
        val mockFileReader = mock<FileReader> { on { readFile(dictionaryPath) } doReturn dictionaryString }
        val mockDictionaryParser =
            mock<DictionaryParser> { on { parse(dictionaryString) } doReturn listOfWords }
        val service = DictionaryLookup(mockDictionaryParser, mockFileReader)
        service.load(dictionaryPath)
        val result = service.lookup("ab")
        result shouldBe listOf("abc")
    }
    "lookup should filter multiple words from the dictionary" {
        val listOfWords = listOf("abc", "bcd", "abba", "ab")
        val dictionaryString = "raw string"
        val dictionaryPath = "path"
        val mockFileReader = mock<FileReader> { on { readFile(dictionaryPath) } doReturn dictionaryString }
        val mockDictionaryParser =
            mock<DictionaryParser> { on { parse(dictionaryString) } doReturn listOfWords }
        val service = DictionaryLookup(mockDictionaryParser, mockFileReader)
        service.load(dictionaryPath)

        val result = service.lookup("ab")
        result shouldBe listOf("abc", "abba", "ab")
    }
})
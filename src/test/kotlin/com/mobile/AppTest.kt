package com.mobile

import InputProcessor
import com.mobile.service.DictionaryLookup
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class AppTest : StringSpec(
    {

        "process should use InputProcessor"{
            val dictionaryString = "raw string"
            val dictionaryPath = "path"
            val input = "22 33"
            val expectedResult = "BE"
            val mockDictionaryLookup = mock<DictionaryLookup>()
            val mockInputProcessor = mock<InputProcessor> { on { process(input) } doReturn expectedResult }

            val app = App(mockInputProcessor, mockDictionaryLookup)

            app.setup(dictionaryPath)
            app.process(input)

            verify(mockInputProcessor).process(input)
        }
        "setup should use DictionaryLookup"{
            val dictionaryString = "raw string"
            val dictionaryPath = "path"
            val input = "22 33"
            val expectedResult = "BE"
            val mockDictionaryLookup = mock<DictionaryLookup>()
            val mockInputProcessor = mock<InputProcessor> { on { process(input) } doReturn expectedResult }

            val app = App(mockInputProcessor, mockDictionaryLookup)

            app.setup(dictionaryPath)

            verify(mockDictionaryLookup).load(dictionaryPath)
        }
        "process should use DictionaryLookup"{
            val dictionaryPath = "path"
            val input = "22 33"
            val processedInput = "BE"
            val mockDictionaryLookup = mock<DictionaryLookup> { on { lookup("BE") } doReturn listOf("BE", "BED") }
            val mockInputProcessor = mock<InputProcessor> { on { process(input) } doReturn processedInput }

            val app = App(mockInputProcessor, mockDictionaryLookup)

            app.setup(dictionaryPath)
            val result = app.process(input)
            verify(mockDictionaryLookup).lookup(processedInput)
            result shouldBe "BE BED"

        }
    })
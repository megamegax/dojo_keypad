package com.mobile.service

import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec

internal class FileReaderTest : StringSpec({
    "readFile should return a notEmpty file"{
        val reader = FileReader()
        val fileContent = reader.readFile("/dictionary.txt")
        fileContent shouldNotBe null
    }
})
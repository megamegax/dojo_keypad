package com.mobile.service

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class DictionaryParserTest : StringSpec({
    "parse" {
        val parser = DictionaryParser()
        val result = parser.parse(
            """alma
            |szilva
            |körte
        """.trimMargin()
        )

        result.size shouldBe 3
        result[0] shouldBe "alma"
        result[1] shouldBe "szilva"
        result[2] shouldBe "körte"
    }
})
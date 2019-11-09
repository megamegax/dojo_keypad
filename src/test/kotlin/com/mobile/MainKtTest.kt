package com.mobile

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class MainKtTest : StringSpec({
    "Main" {
        "hello".length shouldBe 5
    }
})
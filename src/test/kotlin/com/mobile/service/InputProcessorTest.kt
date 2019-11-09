package com.mobile.service

import InputProcessor
import com.mobile.Keypad
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec


internal class InputProcessorTest : StringSpec({
    "input must be numbers"{
        val app = InputProcessor()

        shouldThrow<IllegalArgumentException> {
            app.process("asd")
        }

        shouldNotThrow<java.lang.IllegalArgumentException> {
            app.process("222")
        }
    }
    "input can contain whitespaces"{
        val app = InputProcessor()

        shouldNotThrow<java.lang.IllegalArgumentException> {
            app.process("33 2")
        }
    }

    "process should return A for 2"{
        val app = InputProcessor()
        val result = app.process("2")

        result shouldBe "A"
    }

    "process should use Keypad"{

        val digits = HashMap<Int, Array<Char>>().apply {
            put(3, arrayOf('B'))
        }
        val mockKeypad = Keypad(digits)
        val app = InputProcessor(mockKeypad)

        val result = app.process("3")

        result shouldBe "B"
    }
    "11 should be B"{

        val app = InputProcessor()

        val result = app.process("22")

        result shouldBe "B"
    }
    "22 44 should be BH"{
        val app = InputProcessor()

        val result = app.process("22 44")

        result shouldBe "BH"
    }
})
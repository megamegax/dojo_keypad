package com.mobile.service

import java.io.File

open class FileReader {
    open fun readFile(path: String): String {
        return File(path).readText()
    }
}
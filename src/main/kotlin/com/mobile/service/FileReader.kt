package com.mobile.service

open class FileReader {
    open fun readFile(path: String): String {
        return FileReader::class.java.getResource("/dictionary.txt").readText()
    }
}
package com.mobile

data class Keypad(
    val digits: Map<Int, Array<Char>> = mapOf(
        0 to arrayOf(),
        1 to arrayOf(),
        2 to arrayOf('A', 'B', 'C'),
        3 to arrayOf('D', 'E', 'F'),
        4 to arrayOf('G', 'H', 'I'),
        5 to arrayOf('J', 'K', 'L'),
        6 to arrayOf('M', 'N', 'O'),
        7 to arrayOf('P', 'Q', 'R', 'S'),
        8 to arrayOf('T', 'U', 'V'),
        9 to arrayOf('W', 'X', 'Y', 'Z')
    )
)
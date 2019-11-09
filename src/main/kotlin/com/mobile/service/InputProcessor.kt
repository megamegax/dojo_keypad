import com.mobile.Keypad

open class InputProcessor(private val keypad: Keypad = Keypad()) {
    open fun process(input: String): String {
        if (!input.toCharArray().all { (it.isDigit() || it.isWhitespace()) }) {
            throw IllegalArgumentException("Input must be number")
        }
        var result = ""
        input.split(" ").forEach {
            val l = it.length - 1
            val number = it[0].toString().toInt()
            result += keypad.digits[number]?.get(l)?.toString() ?: "Not Found"
        }
        return result
    }
}
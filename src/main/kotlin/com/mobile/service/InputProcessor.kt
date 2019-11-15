import com.mobile.Keypad

open class InputProcessor(private val keypad: Keypad = Keypad()) {
    open fun process(input: String): String {
        require(input.split(" ").joinToString("").toCharArray().all { (it.isDigit() || it.isISOControl() || it == '\b' || it.isWhitespace()) }) { "Input must be number" }
        var result = ""
        input.trim().split(" ").forEach { str ->
            val textToProcess = str.replace("\b", "")
            try {
                val l = textToProcess.length - 1
                val number = textToProcess[0].toString().toInt()
                result += keypad.digits[number]?.get(l)?.toString() ?: "Not Found"
            } catch (ignored: Exception) {

            }
        }
        return result
    }
}
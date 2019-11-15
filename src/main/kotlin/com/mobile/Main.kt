package com.mobile

import InputProcessor
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.gui2.*
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.mobile.service.DictionaryLookup
import com.mobile.service.DictionaryParser
import com.mobile.service.FileReader


fun main() {
    val app = App(InputProcessor(), DictionaryLookup(DictionaryParser(), FileReader()))
    app.setup("")
    val terminal = DefaultTerminalFactory().createTerminal()
    val screen = TerminalScreen(terminal)
    screen.startScreen()
    val panel = Panel()
    panel.layoutManager = GridLayout(1)
    val size = TerminalSize(terminal.terminalSize.columns - 6, terminal.terminalSize.rows - 6)
    val actionListBox = ActionListBox(size)
    panel.addComponent(actionListBox)
    val textBox = TextField { keyStroke, text ->
        // use only one of handleInput() or handleKeyStroke()
        val input = text + keyStroke?.character.toString()
        val result = app.process(input)
        actionListBox.clearItems()
        result.split(" ").forEach {
            actionListBox.addItem(it, Runnable { })
        }
    }

    panel.addComponent(textBox)

    // Create window to hold the panel
    val window = BasicWindow()
    window.component = panel

    // Create gui and start gui
    val gui = MultiWindowTextGUI(screen, DefaultWindowManager(), EmptySpace(TextColor.ANSI.BLUE))

    gui.addWindowAndWait(window)

}

open class App(
    private val inputProcessor: InputProcessor,
    private val dictionaryLookup: DictionaryLookup
) {

    fun setup(dictionaryPath: String) {
        dictionaryLookup.load(dictionaryPath)
    }

    fun process(input: String): String {
        return if (input.isNotEmpty()) {
            dictionaryLookup.lookup(inputProcessor.process(input)).joinToString(separator = " ")
        } else {
            ""
        }
    }
}
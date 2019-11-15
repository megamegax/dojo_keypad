package com.mobile

import com.googlecode.lanterna.gui2.Interactable
import com.googlecode.lanterna.gui2.TextBox
import com.googlecode.lanterna.input.KeyStroke

class TextField(val inputListener: (KeyStroke?, String) -> Unit) : TextBox() {

    override fun handleKeyStroke(keyStroke: KeyStroke?): Interactable.Result {
        inputListener(keyStroke, this.text)
        return super.handleKeyStroke(keyStroke)
    }
}
package com.sembozdemir.scubanote.extensions

import android.support.annotation.IntegerRes
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import org.jetbrains.anko.enabled

fun FloatingActionButton.setIcon(@IntegerRes iconResId: Int) {
    this.setImageResource(iconResId)
}

fun View.enableEditTexts(enabled: Boolean) {
    for (view in this.getFocusables(View.FOCUS_UP)) {
        when (view) {
            is EditText -> view.enabled = enabled
        }
    }
}

fun EditText.moveCursorEnd() = this.setSelection(this.text.length)

fun EditText.toInt(): Int {
    val string = text.toString()
    if (string.isEmpty())
        return 0
    else
        return string.toInt()
}

fun EditText.asString() = this.text.toString()

fun RadioGroup.enable(enabled: Boolean) {
    for (i in 0..this.childCount - 1) {
        this.getChildAt(i).isEnabled = enabled
    }
}
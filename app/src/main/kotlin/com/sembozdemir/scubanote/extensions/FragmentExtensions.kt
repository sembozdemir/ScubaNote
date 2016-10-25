package com.sembozdemir.scubanote.extensions

import android.support.v4.app.Fragment
import org.jetbrains.anko.support.v4.act

fun Fragment.setTitle(title: String) {
    this.act.title = title
}
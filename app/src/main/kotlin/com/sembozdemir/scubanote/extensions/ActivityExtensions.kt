package com.sembozdemir.scubanote.extensions

import android.support.v7.app.AppCompatActivity


fun AppCompatActivity.enableHomeAsUp() {
    this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}


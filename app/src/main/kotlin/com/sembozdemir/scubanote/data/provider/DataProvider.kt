package com.sembozdemir.scubanote.data.provider

import com.sembozdemir.scubanote.data.model.Dive

interface DataProvider {

    companion object {
        fun get(): DataProvider = HawkProvider()
    }

    fun getDives(): List<Dive>

    fun addDive(dive: Dive)

    fun editDive(no: Int, newDive: Dive)

    fun getDive(no: Int): Dive

    fun getDiveCount(): Int
}
package com.sembozdemir.scubanote.data.model

import java.util.*

data class Dive(
        val name: String,
        val date: Date,
        val dept: String,
        val mins: String,
        val startBar: String,
        val endBar: String,
        val type: String,
        val comments: String
) {
    companion object {
        const val BOAT = "boat"
        const val SHORE = "shore"
    }
}

// this Dive class below is for Realm
/*
open class Dive(
        open var id: Int? = null,

        open var name: String? = null,

        open var date: Date? = null,

        open var dept: Int? = null,

        open var mins: Int? = null,

        open var startBar: Int? = null,

        open var endBar: Int? = null,

        open var type: String? = null,

        open var comments: String? = null
) : RealmObject() {
    companion object {
        const val BOAT = "boat"
        const val SHORE = "shore"
    }
}*/

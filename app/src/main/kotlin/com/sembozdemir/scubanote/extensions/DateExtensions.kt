package com.sembozdemir.scubanote.extensions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    const val SIMPLE_FORMAT_PATTERN = "dd.MM.yyyy"
    val SIMPLE_FORMAT = object : ThreadLocal<DateFormat>() {
        override fun initialValue(): DateFormat {
            return SimpleDateFormat(SIMPLE_FORMAT_PATTERN, Locale.getDefault())
        }
    }

    /**
     * @param string date string in "dd.MM.yyyy" format, e.g "16.10.2016"
     */
    fun create(string: String): Date = DateHelper.SIMPLE_FORMAT.get().parse(string)
}

/**
 * @return date string as "dd.MM.yyyy" format, e.g "16.10.2016"
 */
fun Date.asString(): String = DateHelper.SIMPLE_FORMAT.get().format(this)

val Calendar.year: Int get() = this.get(Calendar.YEAR)
val Calendar.month: Int get() = this.get(Calendar.MONTH)
val Calendar.day: Int get() = this.get(Calendar.DAY_OF_MONTH)
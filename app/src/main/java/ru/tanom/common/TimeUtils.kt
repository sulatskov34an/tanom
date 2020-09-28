package ru.tanom.common

import android.content.Context
import android.text.format.DateUtils

class TimeUtils(val context: Context?) {
    private val YEAR = DateUtils.YEAR_IN_MILLIS

    fun getDateWithYear(context: Context?, seconds: Long): String {
        return DateUtils.formatDateTime(
            context, seconds * 1000L,
            DateUtils.FORMAT_SHOW_YEAR
        )
    }

    fun getDateWithoutYear(context: Context?, seconds: Long): String {
        return DateUtils.formatDateTime(
            context, seconds * 1000L,
            DateUtils.FORMAT_NO_YEAR
        )
    }

    fun getTime(context: Context?, seconds: Long): String {
        return DateUtils.formatDateTime(context, seconds * 1000L, DateUtils.FORMAT_SHOW_TIME)
    }

    //  сегодня, в 12:54 или 20 марта, 12:54
    fun getDateWithInTime(context: Context, seconds: Long): String {
        val date: String
        val diff = System.currentTimeMillis() - seconds * 1000L
        date = if (diff < YEAR) {
            getDateWithoutYear(context, seconds)
        } else {
            getDateWithYear(context, seconds)
        }
        val time = getTime(context, seconds)
        return String.format("%1s, %2s", date, time)
    }
}

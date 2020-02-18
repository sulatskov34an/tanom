package ru.tanom.data.mvvm

import ru.tanom.data.model.Error

data class Event<out T>(val status: Status, val data: List<T>?, val error: Error?) {

    companion object {
        fun <T> loading(): Event<T> {
            return Event(Status.LOADING, null, null)
        }

        fun <T> success(data: List<T>?): Event<T> {
            return Event(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Error?): Event<T> {
            return Event(Status.ERROR, null, error)
        }
    }
}
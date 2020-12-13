package ru.tanom.base.viewmodel

data class Event<out T>(val status: Status, val data: T?, val errorCode: Int?) {

    companion object {
        fun <T> loading(): Event<T> {
            return Event(
                Status.LOADING,
                null,
                null
            )
        }

        fun <T> success(data: T?): Event<T> {
            return Event(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(errorCode: Int): Event<T> {
            return Event(
                Status.ERROR,
                null,
                errorCode
            )
        }
    }
}
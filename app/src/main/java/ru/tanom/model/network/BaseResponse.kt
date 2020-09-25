package ru.tanom.model.network

class BaseResponse<T>(val code: Int, val message: String, val result: T)

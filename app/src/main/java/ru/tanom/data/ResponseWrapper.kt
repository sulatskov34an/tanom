package ru.tanom.data

import com.google.gson.annotations.SerializedName
import ru.tanom.data.model.Error
import java.io.Serializable

class ResponseWrapper<T> : Serializable {
    @SerializedName("response")
    val data: T? = null
    @SerializedName("error")
    val error: Error? = null
}
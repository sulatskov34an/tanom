package ru.tanom.common

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.snackbar.Snackbar


interface ProgressManager {
    fun showProgress()
    fun hideProgress()
}

fun Fragment.toast(msg: String) {
    activity?.apply {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.snackbar(msg: String) {
    view?.apply {
        Snackbar.make(this, msg, Snackbar.LENGTH_LONG).show()    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun getProgressBar(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    return circularProgressDrawable
}

fun getDate(date: String): String{
    return date.replace(" ", " в ")
}
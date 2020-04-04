package ru.tanom.common

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

interface ProgressManager {
    fun showProgress()
    fun hideProgress()
}

fun Fragment.toast(msg: String) {
    activity?.apply {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
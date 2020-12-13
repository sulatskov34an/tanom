package ru.tanom.base.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_search.*
import ru.tanom.R
import ru.tanom.common.snackbar
import ru.tanom.ui.MainActivity

abstract class BaseFragment : Fragment(), BaseViewInterface {

    abstract fun <T> onSuccess(content: T?)

    fun onError() {
        snackbar(getString(R.string.error_text))
        hideProgress()
        showPlaceholder()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    abstract fun showPlaceholder()

    abstract fun hidePlaceholder()

    abstract fun setupToolbar()

    override fun onProgress() {
        (activity as? MainActivity)?.showProgress()
        swipe_container?.isRefreshing = false
    }

    override fun hideProgress() {
        (activity as? MainActivity)?.hideProgress()
        swipe_container?.isRefreshing = false
    }
}
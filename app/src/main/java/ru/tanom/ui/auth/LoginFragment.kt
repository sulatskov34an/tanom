package ru.tanom.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.tanom.R
import ru.tanom.base.view.BaseFragment
import ru.tanom.databinding.FragmentLoginBinding
import ru.tanom.ui.MainActivity

class LoginFragment: BaseFragment() {

    private var fragmentLoginBinding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return fragmentLoginBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.hideBottomNavigation()
    }


    override fun <T> onSuccess(content: T?) {
    }

    override fun showPlaceholder() {
    }

    override fun hidePlaceholder() {
    }

    override fun setupToolbar() {
        fragmentLoginBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        fragmentLoginBinding?.toolbarTitle?.text = getString(R.string.login_enter)
    }

    override fun destroyBinding() {
        fragmentLoginBinding = null
    }
}
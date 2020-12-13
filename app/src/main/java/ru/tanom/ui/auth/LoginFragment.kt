package ru.tanom.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ru.tanom.R
import ru.tanom.base.view.BaseFragment
import ru.tanom.base.viewmodel.Status
import ru.tanom.common.snackbar
import ru.tanom.databinding.FragmentLoginBinding
import ru.tanom.ui.MainActivity

class LoginFragment : BaseFragment() {

    private var fragmentLoginBinding: FragmentLoginBinding? = null
    lateinit var authViewModel: AuthViewModel

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

        fragmentLoginBinding?.loginBtn?.setOnClickListener {
            hideKeyboard()
            val login = fragmentLoginBinding?.loginEt?.text.toString()
            val password = fragmentLoginBinding?.passwordEt?.text.toString()

            authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
            authViewModel.loginResult.observe(this.viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> onProgress()
                    Status.SUCCESS -> onSuccess(it.data?.result)
                    Status.ERROR -> onError()
                }
            })
            authViewModel.login(login, password)

        }
    }

    override fun onError() {
        snackbar(getString(R.string.error_auth_text))
        hideProgress()
    }

    override fun <T> onSuccess(data: T?) {
        hideProgress()
        (data as? String)?.let { snackbar("token is $it") }
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
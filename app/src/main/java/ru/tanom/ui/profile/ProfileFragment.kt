package ru.tanom.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.tanom.R
import ru.tanom.base.view.BaseFragment
import ru.tanom.databinding.FragmentProfileBinding
import ru.tanom.ui.MainActivity

class ProfileFragment : BaseFragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var fragmentProfileBinding: FragmentProfileBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return fragmentProfileBinding?.root
    }

    override fun <T> onSuccess(content: T?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentProfileBinding?.loginOrReg?.setOnClickListener {
            findNavController().navigate(R.id.action_to_auth)
        }
        (activity as? MainActivity)?.showBottomNavigation()
    }

    override fun showPlaceholder() {

    }

    override fun hidePlaceholder() {

    }

    override fun setupToolbar() {
        fragmentProfileBinding?.toolbarTitle?.text = context?.resources?.getText(R.string.profile)
    }

    override fun destroyBinding() {
        fragmentProfileBinding = null
    }
}
package ru.tanom.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import ru.tanom.R
import ru.tanom.base.view.BaseFragment
import ru.tanom.databinding.FragmentProfileBinding
import ru.tanom.databinding.FragmentSearchBinding

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
    }

    override fun showPlaceholder() {

    }

    override fun hidePlaceholder() {

    }

    override fun setToolbar() {
        toolbar.setTitle(R.string.profile)
    }
}